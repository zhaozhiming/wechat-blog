package com.zzm.wechat.controller;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.sina.sae.kvdb.SaeKV;
import com.sina.sae.kvdb.SaeKVUtil;
import com.zzm.wechat.message.MessageGenerator;
import com.zzm.wechat.model.wechat.AccessToken;
import com.zzm.wechat.model.wechat.WechatMessage;
import com.zzm.wechat.util.HttpHelper;
import com.zzm.wechat.util.XmlUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpGet;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;

@Controller
public class MainController {
    private static final Log log = LogFactory.getLog(MainController.class);
    private static final String ACCESS_TOKEN_KEY = "access_token";

    @Value("${token}")
    private String token;

    @Value("${wechat_appid}")
    private String wechatAppid;

    @Value("${wechat_appsecret}")
    private String wechatAppsecret;

    @Autowired
    private HttpHelper httpHelper;

    @Autowired
    private MessageGenerator messageGenerator;

    private ObjectMapper mapper = new ObjectMapper();
    private SaeKV saeKV = new SaeKV();

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> auth(@RequestParam("signature") String signature,
                                @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce,
                                @RequestParam("echostr") String echostr) throws Exception {
        log.info("wechat auth start");
        log.info(String.format("signature:%s, timestamp:%s, nonce:%s, echostr:%s",
                signature, timestamp, nonce, echostr));

        if (wechatAuth(signature, timestamp, nonce)) {
            log.info("wechat auth success");
            return new ResponseEntity<String>(echostr, HttpStatus.OK);
        }

        log.info("wechat auth failed");
        return new ResponseEntity<String>("wechat auth failed.", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> receive(@RequestParam("signature") String signature,
                                   @RequestParam("timestamp") String timestamp,
                                   @RequestParam("nonce") String nonce,
                                   @RequestBody String body) throws Exception {
        log.info("receive message start");
        log.info(String.format("signature:%s, timestamp:%s, nonce:%s", signature, timestamp, nonce));

        if (!wechatAuth(signature, timestamp, nonce)) {
            log.info("wechat auth failed");
            return new ResponseEntity<String>("wechat auth failed.", HttpStatus.BAD_REQUEST);
        }

        log.info(String.format("body:%s", body));
        WechatMessage requestMessage = (WechatMessage) XmlUtil.xmlToObject(body, WechatMessage.class);
        log.info(String.format("requestMessage:%s", requestMessage));

        String responseMessage = XmlUtil.toXml(messageGenerator.createResponseMessage(requestMessage));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        log.info(String.format("response message: %s", responseMessage));
        log.info("receive message finish");
        return new ResponseEntity<String>(responseMessage, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/access_token", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public
    @ResponseBody
    String accessToken() throws Exception {
        log.info("get access token start");
        AccessToken accessToken = refreshAccessToken();
        String result = mapper.writeValueAsString(accessToken);
        log.info(String.format("access token result:%s", result));
        log.info("get access token finish");
        return result;
    }

    private AccessToken refreshAccessToken() throws Exception {
        saeKV.init();
        AccessToken accessToken = SaeKVUtil.deserializable(saeKV.get(ACCESS_TOKEN_KEY));
        log.info(String.format("key value db access token:%s", accessToken));
        if (accessToken == null || isAccessTokenExpire(accessToken)) {
            String url = String.format(
                    "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",
                    wechatAppid, wechatAppsecret);
            HttpGet request = new HttpGet(url);
            String result = httpHelper.baseHttpRequest(request);
            log.info(String.format("wechat access token result:%s", result));
            accessToken = mapper.readValue(result, AccessToken.class);
            accessToken.setRecordAt(System.currentTimeMillis());
            saeKV.set(ACCESS_TOKEN_KEY, SaeKVUtil.serializable(accessToken));
        }
        return accessToken;
    }

    private boolean isAccessTokenExpire(AccessToken accessToken) {
        return (System.currentTimeMillis() - 1000) > (accessToken.getRecordAt() + accessToken.getExpiresIn() * 1000);
    }

    private boolean wechatAuth(String signature, String timestamp, String nonce) {
        ArrayList<String> strings = Lists.newArrayList(token, timestamp, nonce);
        log.info(String.format("before sort array:%s", strings));
        Collections.sort(strings);
        log.info(String.format("after sort array:%s", strings));

        String groupString = Joiner.on("").join(strings);
        log.info(String.format("groupString string:%s", groupString));

        String result = sha1(groupString);
        log.info(String.format("sha1:%s", result));
        boolean compareResult = result.equals(signature.toUpperCase());
        log.info(String.format("compare result:%b", compareResult));
        return compareResult;
    }

    private String sha1(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & aMessageDigest));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("sha1 failed");
        }
    }

}
