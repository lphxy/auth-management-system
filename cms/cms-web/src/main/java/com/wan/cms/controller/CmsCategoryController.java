package com.wan.cms.controller;

import com.wan.cms.dao.model.CmsCategory;
import com.wan.cms.dao.model.CmsCategoryExample;
import com.wan.cms.service.service.CmsCategoryService;
import com.wan.common.util.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by w1992wishes on 2017/7/15.
 */
@Controller
@RequestMapping("/category")
public class CmsCategoryController extends BaseController {
    @Autowired
    private CmsCategoryService cmsCategoryService;

    /**
     * 首页
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "redirect:/category/list";
    }

    /**
     * 列表
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public String list(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "20", value = "rows") int rows,
            HttpServletRequest request, Model model) {

        // 数据列表
        CmsCategoryExample cmsCategoryExample = new CmsCategoryExample();
        cmsCategoryExample.setOffset((page - 1) * rows);
        cmsCategoryExample.setLimit(rows);
        cmsCategoryExample.setOrderByClause("categoryId desc");
        List<CmsCategory> categorys = cmsCategoryService.getMapper().selectByExample(cmsCategoryExample);

        // 分页对象
        long total = cmsCategoryService.getMapper().countByExample(cmsCategoryExample);
        Paginator paginator = new Paginator(total, page, rows, request);

        model.addAttribute("categorys", categorys);
        model.addAttribute("paginator", paginator);
        return "/category/list";
    }

    /**
     * 新增get
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "/category/add";
    }

    /**
     * 新增post
     * @param cmsCategory
     * @param binding
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid CmsCategory cmsCategory, BindingResult binding) {
        if (binding.hasErrors()) {
            for (ObjectError error : binding.getAllErrors()) {
                logger.error(error.getDefaultMessage());
            }
            return "/category/add";
        }
        cmsCategory.setCtime(System.currentTimeMillis());
        cmsCategoryService.getMapper().insertSelective(cmsCategory);
        logger.info("新增记录id为：{}", cmsCategory.getCategoryId());
        return "redirect:/category/list";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        cmsCategoryService.getMapper().deleteByPrimaryKey(id);
        return "redirect:/category/list";
    }

    /**
     * 修改get
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("category", cmsCategoryService.getMapper().selectByPrimaryKey(id));
        return "/category/update";
    }

    /**
     * 修改post
     * @param id
     * @param cmsCategory
     * @param binding
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @Valid CmsCategory cmsCategory, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("errors", binding.getAllErrors());
            return "/category/update/" + id;
        }
        cmsCategoryService.getMapper().updateByPrimaryKeySelective(cmsCategory);
        return "redirect:/category/list";
    }
}
