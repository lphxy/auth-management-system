package com.wan.cms.controller.manage;

import com.wan.cms.controller.BaseController;
import com.wan.cms.dao.model.CmsComment;
import com.wan.cms.dao.model.CmsCommentExample;
import com.wan.cms.service.service.CmsCommentService;
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
@RequestMapping("/manage/comment")
public class CmsCommentController extends BaseController {
    @Autowired
    private CmsCommentService cmsCommentService;

    /**
     * 首页
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "redirect:/manage/comment/list";
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
            @RequestParam(required = false, defaultValue = "true", value = "desc") boolean desc,
            HttpServletRequest request, Model model) {

        // 数据列表
        CmsCommentExample cmsCommentExample = new CmsCommentExample();
        cmsCommentExample.setOffset((page - 1) * rows);
        cmsCommentExample.setLimit(rows);
        cmsCommentExample.setOrderByClause(desc ? "comment_id desc" : "comment_id asc");
        List<CmsComment> comments = cmsCommentService.getMapper().selectByExample(cmsCommentExample);

        // 分页对象
        long total = cmsCommentService.getMapper().countByExample(cmsCommentExample);
        Paginator paginator = new Paginator(total, page, rows, request);

        model.addAttribute("comments", comments);
        model.addAttribute("paginator", paginator);
        return "/manage/comment/list";
    }

    /**
     * 新增get
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "/manage/comment/add";
    }

    /**
     * 新增post
     * @param cmsComment
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(CmsComment cmsComment, Model model) {
        cmsComment.setCtime(System.currentTimeMillis());
        int count = cmsCommentService.getMapper().insertSelective(cmsComment);
        model.addAttribute("count", count);
        logger.info("新增记录id为：{}", cmsComment.getArticleId());
        return "redirect:/manage/comment/list";
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    public String delete(@PathVariable("ids") String ids, Model model) {
        int count = cmsCommentService.deleteByPrimaryKeys(ids);
        model.addAttribute("count", count);
        return "redirect:/manage/comment/list";
    }

    /**
     * 修改get
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, Model model) {
        CmsComment comment = cmsCommentService.getMapper().selectByPrimaryKey(id);
        model.addAttribute("comment", comment);
        return "/manage/comment/update";
    }

    /**
     * 修改post
     * @param id
     * @param cmsComment
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, CmsComment cmsComment, Model model) {
        cmsCommentService.getMapper().updateByPrimaryKeySelective(cmsComment);
        int count = cmsCommentService.getMapper().updateByPrimaryKeySelective(cmsComment);
        model.addAttribute("count", count);
        model.addAttribute("id", id);
        return "redirect:/manage/comment/list";
    }

}
