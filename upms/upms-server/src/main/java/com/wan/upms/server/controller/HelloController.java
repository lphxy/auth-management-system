package com.wan.upms.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by w1992wishes on 2017/8/7.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("")
    @ResponseBody
    public String index() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
    }

}