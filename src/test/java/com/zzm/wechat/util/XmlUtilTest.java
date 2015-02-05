package com.zzm.wechat.util;

import com.zzm.wechat.builder.TextMessageBuilder;
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
        WechatMessage wechatMessage = TextMessageBuilder.aTextMessage().withToUserName("zzm").build();
        wechatMessage.setToUserName("zzm");
        String result = XmlUtil.toXml(wechatMessage);

        assertThat(result, containsString("<ToUserName>zzm</ToUserName>"));
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