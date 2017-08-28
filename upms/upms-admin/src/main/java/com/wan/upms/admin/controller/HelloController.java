package com.wan.upms.admin.controller;

import com.wan.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by w1992wishes on 2017/8/28.
 */
@Controller
@RequestMapping("/hello")
public class HelloController extends BaseController {

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        System.out.println(1/0);
        return "/hello/world";
    }

    @RequestMapping("/index2")
    @ResponseBody
    public String index2() {
        String a = null;
        System.out.println(a.toString());
        return "/hello/world";
    }

}
