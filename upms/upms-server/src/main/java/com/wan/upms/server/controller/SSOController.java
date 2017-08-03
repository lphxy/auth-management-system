package com.wan.upms.server.controller;

import com.wan.common.util.RedisUtil;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by w1992wishes on 2017/7/31.
 */
@Controller
@RequestMapping("/sso")
public class SSOController {

    private static Logger logger = LoggerFactory.getLogger(SSOController.class);
    private static List<String> apps = new ArrayList<>();
    {
        apps.add("cms-job");
        apps.add("cms-admin");
        apps.add("cms-web");
        apps.add("upms-app1");
        apps.add("upms-app2");
        apps.add("upms-server");
    }
    /**
     * 认证中心首页
     * @return
     */
    @RequestMapping("")
    public String index(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        String system_name = request.getParameter("system_name");
        String backurl = request.getParameter("backurl");

        //判断请求认证系统是否注册
        if (StringUtils.isEmpty(system_name) || !apps.contains(system_name)) {
            logger.info("未注册的系统：{}", system_name);
            return "/404";
        }
        //判断是否存在全局会话
        // 未登录
        if (StringUtils.isEmpty(RedisUtil.get(session.getId() + "_token"))) {
            return "redirect:/sso/login?backurl=" + URLEncoder.encode(backurl, "utf-8");
        }
        // 已登录
        String token = RedisUtil.get(session.getId() + "_token");
        String redirectUrl = backurl;
        if (backurl.contains("?")) {
            redirectUrl += "&token=" + token;
        } else {
            redirectUrl += "?token=" + token;
        }
        logger.info("认证中心验证为已登录，跳回：{}", backurl);
        return "redirect:" + backurl;
    }

    /**
     * 登录页get
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/sso/login";
    }

    /**
     * 登录页post
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String backurl = request.getParameter("backurl");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username)) {
            logger.info("帐号不能为空！");
            return "/404";
        }
        if (StringUtils.isEmpty(password)) {
            logger.info("密码不能为空！");
            return "/404";
        }
        // 默认验证帐号密码正确，创建token
        String token = UUID.randomUUID().toString();
        RedisUtil.set(session.getId() + "_token", token, 2*60*60);
        RedisUtil.set(token, token, 2*60*60);
        //回调子系统
        String redirectUrl = backurl;
        if (backurl.contains("?")) {
            redirectUrl += "&token=" + token;
        } else {
            redirectUrl += "?token=" + token;
        }
        logger.info("认证中心帐号通过，带token回跳：{}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    /**
     * 校验token
     * @param request
     * @return
     */
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    @ResponseBody
    public String token(HttpServletRequest request){
        String tokenParam = request.getParameter("token");
        String token = RedisUtil.get(tokenParam);
        if (StringUtils.isEmpty(tokenParam) || !tokenParam.equals(token)){
            return "failed";
        }
        return "success";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();

        //清楚全局会话
        String token = RedisUtil.get(session.getId() + "_token");
        RedisUtil.remove(session.getId() + "_token");
        RedisUtil.remove(token);
        //通知该token的子系统退出登录
        return "/sso/login";
    }
}
