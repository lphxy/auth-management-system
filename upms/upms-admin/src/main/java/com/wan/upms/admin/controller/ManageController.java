package com.wan.upms.admin.controller;

import com.wan.common.base.BaseController;
import com.wan.upms.dao.model.UpmsPermission;
import com.wan.upms.dao.model.UpmsSystem;
import com.wan.upms.dao.model.UpmsSystemExample;
import com.wan.upms.dao.model.UpmsUser;
import com.wan.upms.rpc.api.UpmsApiService;
import com.wan.upms.rpc.api.UpmsSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by w1992wishes on 2017/8/24.
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class ManageController extends BaseController {
    @Autowired
    private UpmsSystemService upmsSystemService;
    @Autowired
    private UpmsApiService upmsApiService;

    @ApiOperation(value = "后台首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        // 已注册系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        List<UpmsSystem> upmsSystems = upmsSystemService.selectByExample(upmsSystemExample);
        modelMap.put("upmsSystems", upmsSystems);
        // 当前登录用户权限
        Subject subject = SecurityUtils.getSubject();
        UpmsUser upmsUser = (UpmsUser) subject.getPrincipal();
        List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
        modelMap.put("upmsPermissions", upmsPermissions);
        return "/manage/index";
    }
}
