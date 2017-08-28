package com.wan.cms.web.controller;

import com.wan.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by w1992wishes on 2017/7/1.
 */
@Controller
@RequestMapping("manage")
public class ManageController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManageController.class);

    @RequestMapping(value = {"", "/index"})
    public String index() {
        return "/manage/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "/manage/login";
    }
}
