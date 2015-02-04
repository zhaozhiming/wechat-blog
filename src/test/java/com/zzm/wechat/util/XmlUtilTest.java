package com.zzm.wechat.util;

import com.zzm.wechat.builder.TextMessageBuilder;
import com.zzm.wechat.model.TextMessage;
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
        TextMessage textMessage = TextMessageBuilder.aTextMessage().withToUserName("zzm").build();
        textMessage.setToUserName("zzm");
        String result = XmlUtil.toXml(textMessage);

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
        TextMessage textMessage = XmlUtil.toTextMessage(xml);

        assertThat(textMessage.getToUserName(), is("zzm"));
        assertThat(textMessage.getFromUserName(), is("zzm"));
        assertThat(textMessage.getCreateTime(), is(123456789L));
        assertThat(textMessage.getMsgType(), is("event"));
        assertThat(textMessage.getEvent(), is("subscribe"));
    }

}