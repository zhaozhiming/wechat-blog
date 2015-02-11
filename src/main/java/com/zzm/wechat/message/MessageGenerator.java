package com.zzm.wechat.message;

import com.google.common.collect.Lists;
import com.zzm.wechat.model.*;
import com.zzm.wechat.model.map.MapResult;
import com.zzm.wechat.model.weather.Weather;
import com.zzm.wechat.util.HttpHelper;
import com.zzm.wechat.util.TimeUtil;
import com.zzm.wechat.util.XmlUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpGet;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

@Component
public class MessageGenerator {
    private static final Log log = LogFactory.getLog(MessageGenerator.class);
    private static final String CITY_ENCODE = "GBK";
    @Autowired
    private HttpHelper httpHelper;

    @Value("${baidu_map_api}")
    private String baiduMapApi;

    @Value("${baidu_ak}")
    private String baiduAk;

    @Value("${sina_weather_api}")
    private String sinaWeatherApi;

    private ObjectMapper mapper = new ObjectMapper();

    public WechatMessage createResponseMessage(WechatMessage requestMessage) throws Exception {
        String msgType = requestMessage.getMsgType();
        String toUserName = requestMessage.getToUserName();
        String fromUserName = requestMessage.getFromUserName();
        if (MessageType.text.name().equals(msgType)) {
            Music music = new Music("Where is The Love", "Black Eyed Peas Justin Timberlake",
                    "http://kingzzmspace.qiniudn.com/818512472000128.mp3",
                    "http://kingzzmspace.qiniudn.com/818512472000128.mp3");
            return new WechatMessage(toUserName, fromUserName,
                    MessageType.music.name(), music, TimeUtil.currentSeconds());
        }

        if (MessageType.event.name().equals(msgType) && EventType.subscribe.name().equals(requestMessage.getEvent())) {
            return new WechatMessage(toUserName, fromUserName,
                    MessageType.news.name(), TimeUtil.currentSeconds(), 1,
                    new Articles(Lists.newArrayList(new Article("感谢您关注我的公众账号", "Hacker and Geeker's Way",
                            "http://zhaozhiming.github.io/images/post/wechat_public_account_2_code.jpg",
                            "http://zhaozhiming.github.io"))));
        }

        if (MessageType.location.name().equals(msgType)) {
            MapResult mapResult = findCityByLocation(requestMessage);
            log.info(String.format("map:%s", mapResult));
            Weather weather = getWeatherByCity(mapResult);
            log.info(String.format("weather:%s", weather));
            String content = String.format("城市: %s\n温度: %s~%s\n天气: %s转%s\n建议:%s",
                    weather.getCity(), weather.getTemperatureMin(), weather.getTemperatureMax(),
                    weather.getStatusFrom(), weather.getStatusTo(), weather.getAdvise());
            log.info(String.format("content:%s", content));
            return new WechatMessage(fromUserName, toUserName, MessageType.text.name(),
                    content, TimeUtil.currentSeconds());
        }
        return null;
    }

    private Weather getWeatherByCity(MapResult mapResult) throws Exception {
        String city = URLEncoder.encode(mapResult.city(), CITY_ENCODE);
        log.info(String.format("city encode:%s", city));
        String url = String.format("%s?city=%s&password=DJOYnieT8234jlsK&day=0", sinaWeatherApi, city);
        log.info(String.format("weather url:%s", url));
        HttpGet request = new HttpGet(url);
        String weatherResult = httpHelper.baseHttpRequest(request);
        log.info(String.format("weather result:%s", weatherResult));
        return XmlUtil.toWeather(weatherResult).weather();
    }

    private MapResult findCityByLocation(WechatMessage requestMessage) throws Exception {
        String latitude = requestMessage.getLocationX();
        String longitude = requestMessage.getLocationY();
        String url = String.format("%s?ak=%s&location=%s,%s&output=json",
                baiduMapApi, baiduAk, latitude, longitude);
        log.info(String.format("location url:%s", url));
        HttpGet request = new HttpGet(url);
        String result = httpHelper.baseHttpRequest(request);
        log.info(String.format("location result:%s", result));
        return mapper.readValue(result, MapResult.class);
    }
}
