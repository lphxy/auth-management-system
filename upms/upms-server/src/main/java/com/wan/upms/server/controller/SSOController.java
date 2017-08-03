package com.wan.upms.server.controller;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by w1992wishes on 2017/7/31.
 */
@Controller
@RequestMapping("/sso")
public class SSOController {

    private static Logger logger = LoggerFactory.getLogger(SSOController.class);

    /**
     * 认证中心首页
     * @return
     */
    @RequestMapping("")
    public String index(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        String system_name = request.getParameter("system_name");
        String backurl = request.getParameter("backurl");
        if (StringUtils.isEmpty(system_name) || !system_name.equals("wan-cms-server")) {
            logger.info("未注册的系统：{}", system_name);
            return "/404";
        }
        // 未登录
        if (null == session.getAttribute("isLogin")) {
            return "redirect:/sso/login?backurl=" + URLEncoder.encode(backurl, "utf-8");
        }
        // 已登录
        String token = ObjectUtils.toString(session.getAttribute(session.getId()));
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
        HttpSession session = request.getSession();
        logger.info("子系统sessionId：{}", session.getId());
        String token = UUID.randomUUID().toString().replace("-", "");
        session.setAttribute("isLogin", true);
        session.setAttribute(session.getId(), token);
        String redirectUrl = backurl;
        if (backurl.contains("?")) {
            redirectUrl += "&token=" + token;
        } else {
            redirectUrl += "?token=" + token;
        }
        logger.info("认证中心帐号通过，带token回跳：{}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

}
