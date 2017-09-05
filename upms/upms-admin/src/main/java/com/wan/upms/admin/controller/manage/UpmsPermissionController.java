package com.wan.upms.admin.controller.manage;

import com.wan.common.base.BaseController;
import com.wan.upms.dao.model.UpmsPermission;
import com.wan.upms.dao.model.UpmsPermissionExample;
import com.wan.upms.rpc.api.UpmsPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限controller
 * <p>
 * Created by w1992wishes on 2017/8/31.
 */
@Controller
@Api(value = "权限管理", description = "权限管理")
@RequestMapping("/manage/permission")
public class UpmsPermissionController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UpmsPermissionController.class);

    @Autowired
    private UpmsPermissionService upmsPermissionService;

    @ApiOperation(value = "权限首页")
    @RequiresPermissions("upms:permission:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/permission/index";
    }

    @ApiOperation(value = "菜单权限首页")
    @RequiresPermissions("upms:permission:read")
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu() {
        return "/manage/permission/menu";
    }

    @ApiOperation(value = "按钮权限首页")
    @RequiresPermissions("upms:permission:read")
    @RequestMapping(value = "/button", method = RequestMethod.GET)
    public String button() {
        return "/manage/permission/button";
    }

    @ApiOperation(value = "权限列表")
    @RequiresPermissions("upms:permission:read")
    @RequestMapping("/list")
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "0", value = "type") int type,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        // 数据列表
        UpmsPermissionExample upmsPermissionExample = new UpmsPermissionExample();
        if (0 != type) {
            upmsPermissionExample.createCriteria()
                    .andTypeEqualTo((byte) type);
        }
        upmsPermissionExample.setOffset(offset);
        upmsPermissionExample.setLimit(limit);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            upmsPermissionExample.setOrderByClause(sort + " " + order);
        }
        List<UpmsPermission> permissions = upmsPermissionService.selectByExample(upmsPermissionExample);
        return permissions;
    }

    @ApiOperation(value = "新增权限")
    @RequiresPermissions("upms:permission:create")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "/manage/permission/add";
    }

    @ApiOperation(value = "新增权限")
    @RequiresPermissions("cms:permission:create")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(UpmsPermission upmsPermission, ModelMap modelMap) {
        long time = System.currentTimeMillis();
        upmsPermission.setCtime(time);
        upmsPermission.setOrders(time);
        int count = upmsPermissionService.insertSelective(upmsPermission);
        modelMap.put("count", count);
        logger.info("新增记录id为：{}", upmsPermission.getPermissionId());
        return "redirect:/manage/permission/list";
    }

    @ApiOperation(value = "删除权限")
    @RequiresPermissions("cms:permission:delete")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    public String delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        int count = upmsPermissionService.deleteByPrimaryKeys(ids);
        modelMap.put("count", count);
        return "redirect:/manage/permission/list";
    }

    @ApiOperation(value = "修改权限")
    @RequiresPermissions("cms:permission:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsPermission permission = upmsPermissionService.selectByPrimaryKey(id);
        modelMap.put("permission", permission);
        return "/manage/permission/update";
    }

    @ApiOperation(value = "修改权限")
    @RequiresPermissions("cms:permission:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, UpmsPermission upmsPermission, ModelMap modelMap) {
        int count = upmsPermissionService.updateByPrimaryKeySelective(upmsPermission);
        modelMap.put("count", count);
        modelMap.put("id", id);
        return "redirect:/manage/permission/list";
    }

}
