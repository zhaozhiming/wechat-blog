package com.zzm.wechat.util;

import org.apache.commons.io.IOUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlUtil {

    public static String toXml(Object object) throws Exception {
        if (object == null) return "";

        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.setProperty(Marshaller.JAXB_FRAGMENT, true);

        StringWriter sw = new StringWriter();
        m.marshal(object, sw);
        return sw.toString();
    }

    public static Object xmlToObject(String xml, Class clasz) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(clasz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        Object object = jaxbUnmarshaller.unmarshal(reader);
        IOUtils.closeQuietly(reader);
        return object;
    }

}
