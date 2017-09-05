package com.wan.upms.admin.controller.manage;

import com.wan.common.base.BaseController;
import com.wan.upms.dao.model.UpmsSystem;
import com.wan.upms.dao.model.UpmsSystemExample;
import com.wan.upms.rpc.api.UpmsSystemService;
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
 * Created by w1992wishes on 2017/8/12.
 */
@Controller
@RequestMapping("/manage/system")
@Api(value = "系统管理", description = "系统管理")
public class UpmsSystemController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(UpmsSystemController.class);

    @Autowired
    private UpmsSystemService upmsSystemService;

    @ApiOperation(value = "系统首页")
    @RequiresPermissions("upms:system:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/system/index";
    }

    @ApiOperation(value = "系统列表")
    @RequiresPermissions("upms:system:read")
    @RequestMapping("/list")
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        // 数据列表
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.setOffset(offset);
        upmsSystemExample.setLimit(limit);
        List<UpmsSystem> systems = upmsSystemService.selectByExample(upmsSystemExample);
        return systems;
    }

    @ApiOperation(value = "新增系统")
    @RequiresPermissions("upms:system:create")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "/manage/system/add";
    }

    @ApiOperation(value = "新增系统")
    @RequiresPermissions("cms:system:create")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(UpmsSystem upmsSystem, ModelMap modelMap) {
        long time = System.currentTimeMillis();
        upmsSystem.setCtime(time);
        upmsSystem.setOrders(time);
        int count = upmsSystemService.insertSelective(upmsSystem);
        modelMap.put("count", count);
        logger.info("新增记录id为：{}", upmsSystem.getSystemId());
        return "redirect:/manage/system/list";
    }

    @ApiOperation(value = "删除系统")
    @RequiresPermissions("cms:system:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    public String delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        int count = upmsSystemService.deleteByPrimaryKeys(ids);
        modelMap.put("count", count);
        return "redirect:/manage/system/list";
    }

    @ApiOperation(value = "修改系统")
    @RequiresPermissions("cms:system:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsSystem system = upmsSystemService.selectByPrimaryKey(id);
        modelMap.put("system", system);
        return "/manage/system/update";
    }

    @ApiOperation(value = "修改系统")
    @RequiresPermissions("cms:system:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, UpmsSystem upmsSystem, ModelMap modelMap) {
        int count = upmsSystemService.updateByPrimaryKeySelective(upmsSystem);
        modelMap.put("count", count);
        modelMap.put("id", id);
        return "redirect:/manage/system/list";
    }
}
