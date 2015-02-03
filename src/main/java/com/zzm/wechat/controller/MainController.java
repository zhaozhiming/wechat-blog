package com.zzm.wechat.controller;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;

@Controller
public class MainController {
    private static final Log log = LogFactory.getLog(MainController.class);

    @Value("${token}")
    private String token;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public
    @ResponseBody
    ResponseEntity<String> index(@RequestParam("signature")String signature,
                 @RequestParam("timestamp")String timestamp,
                 @RequestParam("nonce")String nonce,
                 @RequestParam("echostr")String echostr) throws Exception {
        log.debug("wechat auth start");
        log.debug(String.format("signature:%s, timestamp:%s, nonce:%s, echostr:%s",
                signature, timestamp, nonce, echostr));

        ArrayList<String> strings = Lists.newArrayList(token, timestamp, nonce);
        log.debug(String.format("before sort array:%s", strings));
        Collections.sort(strings);
        log.debug(String.format("after sort array:%s", strings));

        String groupString = Joiner.on("").join(strings);
        log.debug(String.format("groupString string:%s", groupString));

        String result = sha1(groupString);
        log.debug(String.format("sha1:%s", result));
        boolean compareResult = result.equals(signature.toUpperCase());
        log.debug(String.format("compare result:%b", compareResult));

        log.debug("wechat auth finish");
        if (compareResult) {
            return new ResponseEntity<String>(echostr, HttpStatus.OK);
        }

        return new ResponseEntity<String>("wechat auto failed.", HttpStatus.BAD_REQUEST);
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
