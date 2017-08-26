package com.wan.upms.admin.controller.manage;

import com.wan.upms.dao.model.UpmsSystemExample;
import com.wan.upms.rpc.api.UpmsSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("/manage/system")
@Api(value = "系统管理", description = "注册系统管理")
public class SystemController {

    private static Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private UpmsSystemService upmsSystemService;

    @ApiOperation(value = "系统首页")
    @RequiresPermissions("upms.system.read")
    @RequestMapping("/index")
    public String index() {
        return "/manage/system/index";
    }

    @ApiOperation(value = "系统列表")
    @RequiresPermissions("upms.system.read")
    @RequestMapping("/list")
    @ResponseBody
    public Object list() {
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andSystemIdGreaterThan(0);
        return upmsSystemService.selectByExample(upmsSystemExample);
    }
}
