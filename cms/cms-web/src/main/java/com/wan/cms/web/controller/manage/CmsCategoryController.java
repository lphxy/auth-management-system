package com.wan.cms.web.controller.manage;

import com.wan.cms.dao.model.CmsCategory;
import com.wan.cms.dao.model.CmsCategoryExample;
import com.wan.cms.rpc.api.CmsCategoryService;
import com.wan.cms.web.controller.BaseController;
import com.wan.common.util.Paginator;
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
@RequestMapping("/manage/category")
public class CmsCategoryController extends BaseController {
    @Autowired
    private CmsCategoryService cmsCategoryService;

    /**
     * 首页
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "redirect:/manage/category/list";
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
    @RequestMapping("/list")
    public String list(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "20", value = "rows") int rows,
            @RequestParam(required = false, defaultValue = "false", value = "desc") boolean desc,
            HttpServletRequest request, ModelMap modelMap) {

        // 数据列表
        CmsCategoryExample cmsCategoryExample = new CmsCategoryExample();
        cmsCategoryExample.setOffset((page - 1) * rows);
        cmsCategoryExample.setLimit(rows);
        cmsCategoryExample.setOrderByClause(desc ? "orders desc" : "orders asc");
        List<CmsCategory> categorys = cmsCategoryService.selectByExample(cmsCategoryExample);

        // 分页对象
        long total = cmsCategoryService.countByExample(cmsCategoryExample);
        Paginator paginator = new Paginator(total, page, rows, request);

        modelMap.put("categorys", categorys);
        modelMap.put("paginator", paginator);
        return "/manage/category/list";
    }

    /**
     * 新增get
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "/manage/category/add";
    }

    /**
     * 新增post
     * @param cmsCategory
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(CmsCategory cmsCategory, ModelMap modelMap) {
        long time = System.currentTimeMillis();
        cmsCategory.setCtime(time);
        cmsCategory.setOrders(time);
        int count = cmsCategoryService.insertSelective(cmsCategory);
        modelMap.put("count", count);
        logger.info("新增记录id为：{}", cmsCategory.getCategoryId());
        return "redirect:/manage/category/list";
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    public String delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        int count = cmsCategoryService.deleteByPrimaryKeys(ids);
        modelMap.put("count", count);
        return "redirect:/manage/category/list";
    }

    /**
     * 修改get
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        CmsCategory category = cmsCategoryService.selectByPrimaryKey(id);
        modelMap.put("category", category);
        return "/manage/category/update";
    }

    /**
     * 修改post
     * @param id
     * @param cmsCategory
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, CmsCategory cmsCategory, ModelMap modelMap) {
        int count = cmsCategoryService.updateByPrimaryKeySelective(cmsCategory);
        modelMap.put("count", count);
        modelMap.put("id", id);
        return "redirect:/manage/category/list";
    }
}
