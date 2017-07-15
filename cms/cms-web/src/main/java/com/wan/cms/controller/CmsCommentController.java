package com.wan.cms.controller;

import com.wan.cms.dao.model.CmsComment;
import com.wan.cms.dao.model.CmsCommentExample;
import com.wan.cms.service.service.CmsCommentService;
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
@RequestMapping("/comment")
public class CmsCommentController extends BaseController {
    @Autowired
    private CmsCommentService cmsCommentService;

    /**
     * 首页
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "redirect:/comment/list";
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
        CmsCommentExample cmsCommentExample = new CmsCommentExample();
        cmsCommentExample.setOffset((page - 1) * rows);
        cmsCommentExample.setLimit(rows);
        cmsCommentExample.setOrderByClause("comment_id desc");
        List<CmsComment> tags = cmsCommentService.getMapper().selectByExample(cmsCommentExample);

        // 分页对象
        long total = cmsCommentService.getMapper().countByExample(cmsCommentExample);
        Paginator paginator = new Paginator(total, page, rows, request);

        model.addAttribute("tags", tags);
        model.addAttribute("paginator", paginator);
        return "/comment/list";
    }

    /**
     * 新增get
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "/comment/add";
    }

    /**
     * 新增post
     * @param cmsComment
     * @param binding
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid CmsComment cmsComment, BindingResult binding) {
        if (binding.hasErrors()) {
            for (ObjectError error : binding.getAllErrors()) {
                logger.error(error.getDefaultMessage());
            }
            return "/comment/add";
        }
        cmsComment.setCtime(System.currentTimeMillis());
        cmsCommentService.getMapper().insertSelective(cmsComment);
        logger.info("新增记录id为：{}", cmsComment.getArticleId());
        return "redirect:/comment/list";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        cmsCommentService.getMapper().deleteByPrimaryKey(id);
        return "redirect:/comment/list";
    }

    /**
     * 修改get
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("comment", cmsCommentService.getMapper().selectByPrimaryKey(id));
        return "/comment/update";
    }

    /**
     * 修改post
     * @param id
     * @param cmsComment
     * @param binding
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @Valid CmsComment cmsComment, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("errors", binding.getAllErrors());
            return "/comment/update/" + id;
        }
        cmsCommentService.getMapper().updateByPrimaryKeySelective(cmsComment);
        return "redirect:/comment/list";
    }

}
