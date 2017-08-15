package com.wan.upms.server.controller;

import com.wan.common.util.CookieUtil;
import com.wan.common.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private final static Logger logger = LoggerFactory.getLogger(SSOController.class);
    private final static String WAN_UPMS_SSO_SERVER_SESSION_ID = "wan_upms_sso_server_session_id";
    private final static List<String> apps = new ArrayList<>();
    {
        apps.add("wan-cms-job");
        apps.add("wan-cms-server");
        apps.add("wan-cms-web");
        apps.add("wan-upms-app1");
        apps.add("wan-upms-app2");
        apps.add("wan-upms-server");
    }
    /**
     * 认证中心首页
     * @return
     */
    @RequestMapping("")
    public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String system_name = request.getParameter("system_name");
        String backurl = request.getParameter("backurl");

        //判断请求认证系统是否注册
        if (StringUtils.isEmpty(system_name) || !apps.contains(system_name)) {
            logger.info("未注册的系统：{}", system_name);
            return "/404";
        }

        //分配单点登录sessionId，不使用session获取会话id，改为cookie,防止session丢失
        String sessionId = CookieUtil.getCookie(request, WAN_UPMS_SSO_SERVER_SESSION_ID);
        if (StringUtils.isEmpty(sessionId)){
            sessionId = request.getSession().getId();
            CookieUtil.setCookie(response, WAN_UPMS_SSO_SERVER_SESSION_ID);
        }

        //判断是否存在全局会话
        // 未登录
        if (StringUtils.isEmpty(RedisUtil.get(sessionId + "_token"))) {
            return "redirect:/sso/login?backurl=" + URLEncoder.encode(backurl, "utf-8");
        }
        // 已登录
        String token = RedisUtil.get(sessionId + "_token");
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
    public String login(HttpServletRequest request) {
        String sessionId = CookieUtil.getCookie(request, WAN_UPMS_SSO_SERVER_SESSION_ID);
        logger.info("认证中心sessionId={}", sessionId);
        return "/sso/login";
    }

    /**
     * 登录页post
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {
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
        //分配单点登录sessionId，不使用session获取会话id,改为cookie，防止session丢失
        String sessionId = CookieUtil.getCookie(request, WAN_UPMS_SSO_SERVER_SESSION_ID);
        if (StringUtils.isEmpty(sessionId)){
            sessionId = request.getSession().getId();
            CookieUtil.setCookie(response, WAN_UPMS_SSO_SERVER_SESSION_ID, sessionId);
        }

        // 默认验证帐号密码正确，创建token
        String token = UUID.randomUUID().toString();
        RedisUtil.set(sessionId + "_token", token, 2*60*60);
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
