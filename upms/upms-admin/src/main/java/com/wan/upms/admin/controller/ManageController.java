package com.wan.upms.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by w1992wishes on 2017/8/24.
 */
@Controller
@RequestMapping("/manage")
public class ManageController {
    @RequestMapping(value = {"", "/index"})
    public String index(){
        return "/manage/index";
    }
}
