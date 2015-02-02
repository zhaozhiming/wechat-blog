package com.zzm.wechat.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    private static final Log log = LogFactory.getLog(MainController.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${members}")
    private String members;


    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public
    @ResponseBody
    String search() throws Exception {
        log.debug("search blog start");

        log.debug("search blog finish");
        return null;
    }


}
