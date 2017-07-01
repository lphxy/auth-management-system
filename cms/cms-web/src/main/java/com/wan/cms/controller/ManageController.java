package com.wan.cms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by w1992wishes on 2017/7/1.
 */
public class ManageController extends BaseController {

    @RequestMapping(value = {"", "/index"})
    @ResponseBody
    public Object index() {
        return "/manage/index";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Object login() {
        return "/manage/login";
    }
}
