package com.wan.upms.server.controller;

import com.wan.upms.dao.model.UpmsSystemExample;
import com.wan.upms.rpc.api.UpmsSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by w1992wishes on 2017/8/12.
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    private static Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private UpmsSystemService upmsSystemService;

    @RequestMapping("/index")
    public String index() {
        return "/system/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list() {
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andSystemIdGreaterThan(0);
        return upmsSystemService.getMapper().selectByExample(upmsSystemExample);
    }
}
