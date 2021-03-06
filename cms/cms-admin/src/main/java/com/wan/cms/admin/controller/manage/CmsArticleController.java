package com.wan.cms.admin.controller.manage;

import com.wan.cms.common.constant.CmsResult;
import com.wan.cms.common.constant.CmsResultConstant;
import com.wan.cms.dao.model.CmsArticle;
import com.wan.cms.dao.model.CmsArticleExample;
import com.wan.cms.rpc.api.CmsArticleService;
import com.wan.common.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章控制器
 * Created by shuzheng on 2016/11/14.
 */
@Controller
@RequestMapping("/manage/article")
@Api(value = "文章管理", description = "文章管理")
public class CmsArticleController extends BaseController {

    private final static Logger _log = LoggerFactory.getLogger(CmsArticleController.class);

    @Autowired
    private CmsArticleService cmsArticleService;

    @ApiOperation(value = "文章首页")
    @RequiresPermissions("cms:article:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/article/index";
    }

    @ApiOperation(value = "文章列表")
    @RequiresPermissions("cms:article:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        CmsArticleExample cmsArticleExample = new CmsArticleExample();
        cmsArticleExample.setOffset(offset);
        cmsArticleExample.setLimit(limit);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            cmsArticleExample.setOrderByClause(sort + " " + order);
        }
        List<CmsArticle> rows = cmsArticleService.selectByExample(cmsArticleExample);
        long total = cmsArticleService.countByExample(cmsArticleExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增文章")
    @RequiresPermissions("cms:article:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/article/create";
    }

    @ApiOperation(value = "新增文章")
    @RequiresPermissions("cms:article:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(CmsArticle cmsArticle) {
        long time = System.currentTimeMillis();
        cmsArticle.setCtime(time);
        cmsArticle.setOrders(time);
        int count = cmsArticleService.insertSelective(cmsArticle);
        return new CmsResult(CmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除文章")
    @RequiresPermissions("cms:article:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = cmsArticleService.deleteByPrimaryKeys(ids);
        return new CmsResult(CmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改文章")
    @RequiresPermissions("cms:article:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        CmsArticle article = cmsArticleService.selectByPrimaryKey(id);
        modelMap.put("article", article);
        return "/manage/article/update";
    }

    @ApiOperation(value = "修改文章")
    @RequiresPermissions("cms:article:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, CmsArticle cmsArticle) {
        cmsArticle.setArticleId(id);
        int count = cmsArticleService.updateByPrimaryKeySelective(cmsArticle);
        return new CmsResult(CmsResultConstant.SUCCESS, count);
    }
}