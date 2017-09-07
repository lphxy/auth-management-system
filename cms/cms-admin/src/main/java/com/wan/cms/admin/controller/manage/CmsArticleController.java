package com.wan.cms.admin.controller.manage;

import com.wan.cms.dao.model.CmsArticle;
import com.wan.cms.dao.model.CmsArticleExample;
import com.wan.cms.rpc.api.CmsArticleService;
import com.wan.common.base.BaseController;
import com.wan.common.util.Paginator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by w1992wishes on 2017/7/15.
 */
@Controller
@RequestMapping("/manage/article")
@Api(value = "文章控制器", description = "文章管理")
public class CmsArticleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsArticleController.class);

    @Autowired
    private CmsArticleService cmsArticleService;

    /**
     * 首页
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "redirect:/manage/article/list";
    }

    /**
     *
     * @param page 当前页码
     * @param rows 每页条数
     * @param desc 降序排序
     * @param request
     * @param modelMap
     * @return
     */
    @ApiOperation(value = "文章列表", notes = "获取文章列表并分页")
    @RequiresPermissions("cms:article:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "20", value = "rows") int rows,
            @RequestParam(required = false, defaultValue = "true", value = "desc") boolean desc,
            HttpServletRequest request, ModelMap modelMap) {

        // 数据列表
        CmsArticleExample cmsArticleExample = new CmsArticleExample();
        cmsArticleExample.setOffset((page - 1) * rows);
        cmsArticleExample.setLimit(rows);
        cmsArticleExample.setOrderByClause(desc ? "orders desc" : "orders asc");
        List<CmsArticle> articles = cmsArticleService.selectByExample(cmsArticleExample);

        // 分页对象
        long total = cmsArticleService.countByExample(cmsArticleExample);
        Paginator paginator = new Paginator(total, page, rows, request);

        modelMap.put("articles", articles);
        modelMap.put("paginator", paginator);
        return "/manage/article/list";
    }

    /**
     * 新增get
     * @return
     */
    @ApiOperation(value = "新增文章", notes = "新增文章页")
    @RequiresPermissions("cms:article:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/article/create";
    }

    /**
     * 新增post
     * @param cmsArticle
     * @param modelMap
     * @return
     */
    @ApiOperation(value = "新增文章", notes = "新增文章提交接口")
    @RequiresPermissions("cms:article:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(CmsArticle cmsArticle, ModelMap modelMap) {
        long time = System.currentTimeMillis();
        cmsArticle.setCtime(time);
        cmsArticle.setOrders(time);
        int count = cmsArticleService.insertSelective(cmsArticle);
        modelMap.put("count", count);
        LOGGER.info("新增记录id为：{}", cmsArticle.getArticleId());
        return "redirect:/manage/article/list";
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除文章", notes = "批量删除文章")
    @RequiresPermissions("cms:article:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    public String delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        int count = cmsArticleService.deleteByPrimaryKeys(ids);
        modelMap.put("count",count);
        return "redirect:/manage/article/list";
    }

    /**
     * 修改get
     * @param id
     * @param modelMap
     * @return
     */
    @ApiOperation(value = "修改文章", notes = "根据id修改文章页")
    @RequiresPermissions("cms:article:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        CmsArticle article = cmsArticleService.selectByPrimaryKey(id);
        modelMap.put("article", article);
        return "/manage/article/update";
    }

    /**
     * 修改post
     * @param id
     * @param cmsArticle
     * @param modelMap
     * @return
     */
    @ApiOperation(value = "修改文章", notes = "根据id修改文章提交接口")
    @RequiresPermissions("cms:article:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, CmsArticle cmsArticle, ModelMap modelMap) {
        int count = cmsArticleService.updateByPrimaryKeySelective(cmsArticle);
        modelMap.put("count", count);
        modelMap.put("id", id);
        return "redirect:/manage/article/list";
    }
}
