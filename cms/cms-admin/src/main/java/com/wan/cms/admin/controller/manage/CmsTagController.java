package com.wan.cms.admin.controller.manage;

import com.wan.cms.dao.model.CmsTag;
import com.wan.cms.dao.model.CmsTagExample;
import com.wan.cms.rpc.api.CmsTagService;
import com.wan.cms.admin.controller.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by w1992wishes on 2017/7/15.
 */
@Controller
@RequestMapping("/manage/tag")
public class CmsTagController extends BaseController {

    @Autowired
    private CmsTagService cmsTagService;

    @RequestMapping("/index")
    public String index() {
        return "/manage/tag/index";
    }

    /**
     * 列表
     * @param offset
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {

        // 数据列表
        CmsTagExample cmsTagExample = new CmsTagExample();
        cmsTagExample.setOffset(offset);
        cmsTagExample.setLimit(limit);
        cmsTagExample.setOrderByClause(sort + " " + order);
        List<CmsTag> tags = cmsTagService.selectByExample(cmsTagExample);

        return tags;
    }

    /**
     * 新增get
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "/manage/tag/add";
    }

    /**
     * 新增post
     * @param cmsTag
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(CmsTag cmsTag, ModelMap modelMap) {
        long time = System.currentTimeMillis();
        cmsTag.setCtime(time);
        cmsTag.setOrders(time);
        int count = cmsTagService.insertSelective(cmsTag);
        modelMap.put("count", count);
        logger.info("新增记录id为：{}", cmsTag.getTagId());
        return "redirect:/manage/tag/list";
    }

    /**
     * 删除
     * @param ids
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    public String delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        int count = cmsTagService.deleteByPrimaryKeys(ids);
        modelMap.put("count", count);
        return "redirect:/manage/tag/list";
    }

    /**
     * 修改get
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        CmsTag tag = cmsTagService.selectByPrimaryKey(id);
        modelMap.put("tag", tag);
        return "/manage/tag/update";
    }

    /**
     * 修改post
     * @param id
     * @param cmsTag
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, CmsTag cmsTag, ModelMap modelMap) {
        int count = cmsTagService.updateByPrimaryKeySelective(cmsTag);
        modelMap.put("count", count);
        modelMap.put("id", id);
        return "redirect:/manage/tag/list";
    }
}