package com.zzm.wechat.util;

import com.google.common.collect.Lists;
import com.zzm.wechat.builder.ArticleBuilder;
import com.zzm.wechat.builder.WechatMessageBuilder;
import com.zzm.wechat.model.wechat.Articles;
import com.zzm.wechat.model.wechat.WechatMessage;
import com.zzm.wechat.model.weather.Weather;
import com.zzm.wechat.model.weather.Weathers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class XmlUtilTest {

    @Test
    public void should_return_empty_string_when_given_null() throws Exception {
        assertThat(XmlUtil.toXml(null), is(""));
    }

    @Test
    public void should_convert_message_to_xml_correct() throws Exception {
        WechatMessage wechatMessage = WechatMessageBuilder.aWechatMessage().withToUserName("zzm").build();
        String result = XmlUtil.toXml(wechatMessage);

        assertThat(result, containsString("<ToUserName>zzm</ToUserName>"));
    }

    @Test
    public void should_convert_news_message_to_xml_correct() throws Exception {
        Articles articles = new Articles(Lists.newArrayList(
                ArticleBuilder.aArticle().withTitle("title1").build(),
                ArticleBuilder.aArticle().withTitle("title2").build()
        ));
        WechatMessage wechatMessage = WechatMessageBuilder.aWechatMessage()
                .withArticles(articles).build();
        String result = XmlUtil.toXml(wechatMessage);

        assertThat(result, containsString("    <Articles>\n" +
                "        <item>\n" +
                "            <Title>title1</Title>\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <Title>title2</Title>\n" +
                "        </item>\n" +
                "    </Articles>"));
    }

    @Test
    public void should_convert_xml_to_message_correct() throws Exception {
        String xml = "<xml>\n" +
                "<ToUserName>zzm</ToUserName>\n" +
                "<FromUserName>zzm</FromUserName>\n" +
                "<CreateTime>123456789</CreateTime>\n" +
                "<MsgType>event</MsgType>\n" +
                "<Event>subscribe</Event>\n" +
                "</xml>";
        WechatMessage wechatMessage = (WechatMessage) XmlUtil.xmlToObject(xml, WechatMessage.class);

        assertThat(wechatMessage.getToUserName(), is("zzm"));
        assertThat(wechatMessage.getFromUserName(), is("zzm"));
        assertThat(wechatMessage.getCreateTime(), is(123456789L));
        assertThat(wechatMessage.getMsgType(), is("event"));
        assertThat(wechatMessage.getEvent(), is("subscribe"));
    }

    @Test
    public void should_covert_xml_to_weather_correct() throws Exception {
        String xml = "<Profiles>\n" +
                "    <Weather>\n" +
                "        <city>北京</city>\n" +
                "        <status1>晴</status1>\n" +
                "        <status2>晴</status2>\n" +
                "        <temperature1>8</temperature1>\n" +
                "        <temperature2>-4</temperature2>\n" +
                "        <ssd_s>老年、幼儿、体弱者外出需要带上薄围巾、薄手套。</ssd_s>\n" +
                "        <savedate_weather>2015-02-12</savedate_weather>\n" +
                "    </Weather>\n" +
                "</Profiles>";

        Weathers weathers = (Weathers) XmlUtil.xmlToObject(xml, Weathers.class);
        assertThat(weathers.getWeathers().size(), is(1));
        Weather weather = weathers.getWeathers().get(0);
        System.out.println(weather);
        assertThat(weather.getCity(), is("北京"));
        assertThat(weather.getStatusFrom(), is("晴"));
        assertThat(weather.getStatusTo(), is("晴"));
        assertThat(weather.getDate(), is("2015-02-12"));
        assertThat(weather.getTemperatureMin(), is("-4"));
        assertThat(weather.getTemperatureMax(), is("8"));
        assertThat(weather.getAdvise(), is("老年、幼儿、体弱者外出需要带上薄围巾、薄手套。"));
    }

}