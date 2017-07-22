package com.wan.cms.controller;

import com.wan.common.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * redis 缓存controller
 *
 * Created by w1992wishes on 2017/7/22.
 */
@Controller
@RequestMapping("/redis")
public class RedisController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @RequestMapping("/test")
    @ResponseBody
    public Object test(HttpServletRequest request) {
        System.out.println(System.getProperty("java.io.tmpdir"));
        for (int i = 1; i <= 10000; i ++) {
            RedisUtil.set("key" + i, "value" + i, i * 5);
        }
        return "success";
    }

    /**
     * 新增缓存记录
     *
     * @param request
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(HttpServletRequest request) {
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        String time = request.getParameter("time");
        RedisUtil.set(key, value, Integer.parseInt(time));
        return "success";
    }

    /**
     * 删除缓存记录
     *
     * @param request
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public Object remove(HttpServletRequest request) {
        String key = request.getParameter("key");
        RedisUtil.remove(key);
        return "success";
    }

    /**
     * 获取缓存记录
     *
     * @param request
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public Object get(HttpServletRequest request) {
        String key = request.getParameter("key");
        String value = RedisUtil.get(key);
        if (null == value) {
            logger.debug("【redis】没有找到key={}的记录！", key);
            return "";
        }
        return value;
    }

}
