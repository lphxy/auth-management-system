package com.wan.cms.web.controller;

import com.wan.common.util.EhCacheUtil;
import com.wan.common.util.PropertiesFileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 缓存controller
 *
 * Created by w1992wishes on 2017/6/28.
 */
@Controller
@RequestMapping("/ehcache")
public class EhCacheController extends BaseController {
    //cache name
    private final static String CACHE_NAME = PropertiesFileUtil.getInstance().get("ehcache");

    @RequestMapping("/test")
    @ResponseBody
    public Object test(HttpServletRequest request) {
        System.out.println(System.getProperty("java.io.tmpdir"));
        return "success";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object add(HttpServletRequest request){
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        EhCacheUtil.put(CACHE_NAME, key, value);
        return SUCCESS;
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get(HttpServletRequest request){
        String key = request.getParameter("key");
        Object object = EhCacheUtil.get(CACHE_NAME, key);
        if (null == object){
            logger.debug("【Ehcache】没有找到key={}的记录！", key);
            return FAILED;
        }
        return object;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Object remove(HttpServletRequest request){
        String key = request.getParameter("key");
        EhCacheUtil.remove(CACHE_NAME, key);
        return SUCCESS;
    }
}

