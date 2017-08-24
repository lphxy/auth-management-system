package com.wan.upms.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by w1992wishes on 2017/8/24.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    /**
     * 默认页跳转到后台
     *
     * @return
     */
    @RequestMapping(value = {"", "/index"})
    public String index() {
        return "redirect:/manage";
    }
}
