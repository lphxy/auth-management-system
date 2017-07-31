package com.wan.cms.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by w1992wishes on 2017/7/31.
 */
@Controller
@RequestMapping("/manage/user")
public class UserController extends BaseController {
    /**
     * 列表
     * @return
     */
    @RequestMapping("/list")
    public String index() {
        return "/user/list";
    }

}
