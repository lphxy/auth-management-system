package com.wan.upms.admin.controller.manage;

import com.wan.common.base.BaseController;
import com.wan.upms.dao.model.UpmsOrganization;
import com.wan.upms.dao.model.UpmsOrganizationExample;
import com.wan.upms.rpc.api.UpmsOrganizationService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组织controller
 *
 * Created by w1992wishes on 2017/8/31.
 */
@Controller
@Api(value = "组织管理", description = "组织管理")
@RequestMapping("/manage/organization")
public class UpmsOrganizationController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UpmsOrganizationController.class);

    @Autowired
    private UpmsOrganizationService upmsOrganizationService;

    @ApiOperation(value = "组织首页")
    @RequiresPermissions("upms:organization:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/organization/index";
    }

    @ApiOperation(value = "组织列表")
    @RequiresPermissions("upms:organization:read")
    @RequestMapping("/list")
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        // 数据列表
        UpmsOrganizationExample upmsOrganizationExample = new UpmsOrganizationExample();
        upmsOrganizationExample.setOffset(offset);
        upmsOrganizationExample.setLimit(limit);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            upmsOrganizationExample.setOrderByClause(sort + " " + order);
        }
        List<UpmsOrganization> rows = upmsOrganizationService.selectByExample(upmsOrganizationExample);
        long total = upmsOrganizationService.countByExample(upmsOrganizationExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增组织")
    @RequiresPermissions("upms:organization:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/organization/create";
    }

    @ApiOperation(value = "新增组织")
    @RequiresPermissions("cms:organization:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(UpmsOrganization upmsOrganization, ModelMap modelMap) {
        long time = System.currentTimeMillis();
        upmsOrganization.setCtime(time);
        int count = upmsOrganizationService.insertSelective(upmsOrganization);
        modelMap.put("count", count);
        logger.info("新增记录id为：{}", upmsOrganization.getOrganizationId());
        return "redirect:/manage/organization/list";
    }

    @ApiOperation(value = "删除组织")
    @RequiresPermissions("cms:organization:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    public String delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        int count = upmsOrganizationService.deleteByPrimaryKeys(ids);
        modelMap.put("count", count);
        return "redirect:/manage/organization/list";
    }

    @ApiOperation(value = "修改组织")
    @RequiresPermissions("cms:organization:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsOrganization organization = upmsOrganizationService.selectByPrimaryKey(id);
        modelMap.put("organization", organization);
        return "/manage/organization/update";
    }

    @ApiOperation(value = "修改组织")
    @RequiresPermissions("cms:organization:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, UpmsOrganization upmsOrganization, ModelMap modelMap) {
        int count = upmsOrganizationService.updateByPrimaryKeySelective(upmsOrganization);
        modelMap.put("count", count);
        modelMap.put("id", id);
        return "redirect:/manage/organization/list";
    }

}
