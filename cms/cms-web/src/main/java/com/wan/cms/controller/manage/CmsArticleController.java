package com.wan.cms.controller.manage;

import com.wan.cms.controller.BaseController;
import com.wan.cms.dao.model.CmsArticle;
import com.wan.cms.dao.model.CmsArticleExample;
import com.wan.cms.service.service.CmsArticleService;
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
@RequestMapping("/manage/article")
public class CmsArticleController extends BaseController {
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
        CmsArticleExample cmsArticleExample = new CmsArticleExample();
        cmsArticleExample.setOffset((page - 1) * rows);
        cmsArticleExample.setLimit(rows);
        cmsArticleExample.setOrderByClause("orders desc");
        List<CmsArticle> articles = cmsArticleService.getMapper().selectByExample(cmsArticleExample);

        // 分页对象
        long total = cmsArticleService.getMapper().countByExample(cmsArticleExample);
        Paginator paginator = new Paginator(total, page, rows, request);

        model.addAttribute("articles", articles);
        model.addAttribute("paginator", paginator);
        return "/manage/article/list";
    }

    /**
     * 新增get
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "/manage/article/add";
    }

    /**
     * 新增post
     * @param cmsArticle
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(CmsArticle cmsArticle, Model model) {
        long time = System.currentTimeMillis();
        cmsArticle.setCtime(time);
        cmsArticle.setOrders(time);
        int count = cmsArticleService.getMapper().insertSelective(cmsArticle);
        model.addAttribute("count", count);
        logger.info("新增记录id为：{}", cmsArticle.getArticleId());
        return "redirect:/manage/article/list";
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    public String delete(@PathVariable("ids") String ids, Model model) {
        int count = cmsArticleService.deleteByPrimaryKeys(ids);
        model.addAttribute("count",count);
        return "redirect:/manage/article/list";
    }

    /**
     * 修改get
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, Model model) {
        CmsArticle article = cmsArticleService.getMapper().selectByPrimaryKey(id);
        model.addAttribute("article", article);
        return "/manage/article/update";
    }

    /**
     * 修改post
     * @param id
     * @param cmsArticle
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, CmsArticle cmsArticle, Model model) {
        int count = cmsArticleService.getMapper().updateByPrimaryKeySelective(cmsArticle);
        cmsArticleService.getMapper().updateByPrimaryKeySelective(cmsArticle);
        model.addAttribute("count", count);
        model.addAttribute("id", id);
        return "redirect:/manage/article/list";
    }
}