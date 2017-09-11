package com.wan.cms.admin.controller;

import com.wan.cms.common.constant.CmsResult;
import com.wan.cms.common.constant.CmsResultConstant;
import com.wan.cms.dao.model.CmsUser;
import com.wan.cms.dao.model.CmsUserExample;
import com.wan.cms.rpc.api.CmsUserService;
import com.wan.common.base.BaseController;
import com.wan.common.util.Paginator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by w1992wishes on 2017/9/11.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CmsUserService userService;

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception e) {
        e.printStackTrace();
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = {"", "index"})
    public String index() {
        return "redirect:/user/list";
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
            HttpServletRequest request, ModelMap modelMap) {

        CmsUserExample userExample = new CmsUserExample();
        userExample.createCriteria()
                .andUserIdGreaterThan(0);
        userExample.setOffset((page -1) * rows);
        userExample.setLimit(rows);
        userExample.setDistinct(false);
        userExample.setOrderByClause(" user_id asc ");
        List<CmsUser> users = userService.selectByExample(userExample);
        modelMap.put("users", users);

        // 创建分页对象
        long total = userService.countByExample(userExample);
        Paginator paginator = new Paginator();
        paginator.setTotal(total);
        paginator.setPage(page);
        paginator.setRows(rows);
        paginator.setParam("page");
        paginator.setUrl(request.getRequestURI());
        paginator.setQuery(request.getQueryString());
        modelMap.put("paginator", paginator);

        return "/user/list";
    }

    /**
     * 新增get
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String add() {
        return "/user/create";
    }

    /**
     * 新增post
     * @param user
     * @param binding
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String add(@Valid CmsUser user, BindingResult binding) {
        if (binding.hasErrors()) {
            for (ObjectError error : binding.getAllErrors()) {
                logger.error(error.getDefaultMessage());
            }
            return "/user/create";
        }
        user.setCtime(System.currentTimeMillis());

        userService.insertSelective(user);

        logger.info("新增记录id为：{}", user.getUserId());

        return "redirect:/user/list";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        userService.deleteByPrimaryKey(id);
        return "redirect:/user/list";
    }

    /**
     * 修改get
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("user", userService.selectByPrimaryKey(id));
        return "/user/update";
    }

    /**
     * 修改post
     * @param id
     * @param user
     * @param binding
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @Valid CmsUser user, BindingResult binding, ModelMap modelMap) {
        if (binding.hasErrors()) {
            modelMap.put("errors", binding.getAllErrors());
            return "user/update/" + id;
        }
        userService.updateByPrimaryKeySelective(user);
        return "redirect:/user/list";
    }

    /**
     * 上传文件
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Object upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {
        // 返回结果
        Map<String, Object> map = new HashMap<String, Object>();
        // 判断上传文件类型
        String contentType = file.getContentType().toLowerCase();
        if ((!contentType.equals("image/jpeg")) &&
                (!contentType.equals("image/pjpeg")) &&
                (!contentType.equals("image/png")) &&
                (!contentType.equals("image/x-png")) &&
                (!contentType.equals("image/bmp")) &&
                (!contentType.equals("image/gif"))) {
            return new CmsResult(CmsResultConstant.FILE_TYPE_ERROR, "不支持该类型的文件！");

        }
        // 创建图片目录
        String basePath = request.getSession().getServletContext().getRealPath("/attached");
        String fileName = file.getOriginalFilename();
        String savePath = basePath + "/images/";
        File targetFile = new File(savePath, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 保存图片
        file.transferTo(targetFile);
        return new CmsResult(CmsResultConstant.SUCCESS, targetFile.getAbsoluteFile());
    }

    /**
     * ajax
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajax/{id}", method = RequestMethod.GET)
    public Object ajax(@PathVariable int id) {
        return userService.selectByPrimaryKey(id);
    }
}
