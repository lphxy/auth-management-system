package com.wan.cms.admin.controller;

import com.wan.common.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by w1992wishes on 2017/7/1.
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台控制器", description = "后台管理")
public class ManageController extends BaseController {

    @ApiOperation(value = "后台首页")
    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "/manage/index";
    }

}
