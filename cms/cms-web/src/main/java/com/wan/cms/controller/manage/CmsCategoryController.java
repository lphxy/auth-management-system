package com.wan.cms.controller.manage;

import com.wan.cms.controller.BaseController;
import com.wan.cms.dao.model.CmsCategory;
import com.wan.cms.dao.model.CmsCategoryExample;
import com.wan.cms.service.service.CmsCategoryService;
import com.wan.common.util.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "20", value = "rows") int rows,
            @RequestParam(required = false, defaultValue = "false", value = "desc") boolean desc,
            HttpServletRequest request, Model model) {

        // 数据列表
        CmsCategoryExample cmsCategoryExample = new CmsCategoryExample();
        cmsCategoryExample.setOffset((page - 1) * rows);
        cmsCategoryExample.setLimit(rows);
        cmsCategoryExample.setOrderByClause(desc ? "orders desc" : "orders asc");
        List<CmsCategory> categorys = cmsCategoryService.getMapper().selectByExample(cmsCategoryExample);

        // 分页对象
        long total = cmsCategoryService.getMapper().countByExample(cmsCategoryExample);
        Paginator paginator = new Paginator(total, page, rows, request);

        model.addAttribute("categorys", categorys);
        model.addAttribute("paginator", paginator);
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
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(CmsCategory cmsCategory, Model model) {
        long time = System.currentTimeMillis();
        cmsCategory.setCtime(time);
        cmsCategory.setOrders(time);
        int count = cmsCategoryService.getMapper().insertSelective(cmsCategory);
        model.addAttribute("count", count);
        logger.info("新增记录id为：{}", cmsCategory.getCategoryId());
        return "redirect:/manage/category/list";
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    public String delete(@PathVariable("ids") String ids, Model model) {
        int count = cmsCategoryService.deleteByPrimaryKeys(ids);
        model.addAttribute("count", count);
        return "redirect:/manage/category/list";
    }

    /**
     * 修改get
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, Model model) {
        CmsCategory category = cmsCategoryService.getMapper().selectByPrimaryKey(id);
        model.addAttribute("category", category);
        return "/manage/category/update";
    }

    /**
     * 修改post
     * @param id
     * @param cmsCategory
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, CmsCategory cmsCategory, Model model) {
        int count = cmsCategoryService.getMapper().updateByPrimaryKeySelective(cmsCategory);
        model.addAttribute("count", count);
        model.addAttribute("id", id);
        return "redirect:/manage/category/list";
    }
}
