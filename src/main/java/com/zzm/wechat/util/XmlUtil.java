package com.zzm.wechat.util;

import com.zzm.wechat.model.WechatMessage;
import com.zzm.wechat.model.weather.Weathers;
import org.apache.commons.io.IOUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlUtil {

    public static String toXml(WechatMessage wechatMessage) throws Exception {
        if (wechatMessage == null) return "";

        JAXBContext context = JAXBContext.newInstance(WechatMessage.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.setProperty(Marshaller.JAXB_FRAGMENT, true);

        StringWriter sw = new StringWriter();
        m.marshal(wechatMessage, sw);
        return sw.toString();
    }

    public static WechatMessage toMessage(String xml) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(WechatMessage.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        WechatMessage wechatMessage = (WechatMessage) jaxbUnmarshaller.unmarshal(reader);
        IOUtils.closeQuietly(reader);
        return wechatMessage;
    }

    public static Weathers toWeather(String xml) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Weathers.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        Weathers weathers = (Weathers) jaxbUnmarshaller.unmarshal(reader);
        IOUtils.closeQuietly(reader);
        return weathers;
    }
}
