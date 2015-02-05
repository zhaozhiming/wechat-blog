package com.zzm.wechat.util;

import com.google.common.collect.Lists;
import com.zzm.wechat.builder.ArticleBuilder;
import com.zzm.wechat.builder.WechatMessageBuilder;
import com.zzm.wechat.model.Articles;
import com.zzm.wechat.model.WechatMessage;
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
        WechatMessage wechatMessage = XmlUtil.toTextMessage(xml);

        assertThat(wechatMessage.getToUserName(), is("zzm"));
        assertThat(wechatMessage.getFromUserName(), is("zzm"));
        assertThat(wechatMessage.getCreateTime(), is(123456789L));
        assertThat(wechatMessage.getMsgType(), is("event"));
        assertThat(wechatMessage.getEvent(), is("subscribe"));
    }

}