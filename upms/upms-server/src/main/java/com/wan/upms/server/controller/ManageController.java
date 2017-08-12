package com.wan.upms.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by w1992wishes on 2017/8/12.
 */
@Controller
@RequestMapping("/manage")
public class ManageController {

    private static Logger logger = LoggerFactory.getLogger(ManageController.class);

    @RequestMapping(value = {"", "/index"})
    public String index() {
        return "/manage/index";
    }

}
