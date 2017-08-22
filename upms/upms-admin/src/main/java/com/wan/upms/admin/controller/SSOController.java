package com.wan.upms.admin.controller;

import com.wan.common.util.CookieUtil;
import com.wan.common.util.RedisUtil;
import com.wan.upms.dao.model.UpmsSystemExample;
import com.wan.upms.rpc.api.UpmsSystemService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by w1992wishes on 2017/7/31.
 */
@Controller
@RequestMapping("/sso")
public class SSOController {

    @Autowired
    private UpmsSystemService upmsSystemService;

    private final static Logger logger = LoggerFactory.getLogger(SSOController.class);
    private final static int TIMEOUT = 2 * 60 * 60;
    private final static String WAN_UPMS_SSO_SERVER_SESSION_ID = "wan_upms_sso_server_session_id";

    /**
     * 认证中心首页
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String system_name = request.getParameter("system_name");
        String backurl = request.getParameter("backurl");

        //判断请求认证系统是否注册
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria().andNameEqualTo(system_name);
        int count = upmsSystemService.countByExample(upmsSystemExample);
        if (StringUtils.isEmpty(system_name) || 0 == count) {
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
        return "redirect:" + redirectUrl;
    }

    /**
     * 登录页get
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        String sessionId = CookieUtil.getCookie(request, WAN_UPMS_SSO_SERVER_SESSION_ID);
        logger.info("认证中心sessionId={}", sessionId);
        String backurl = request.getParameter("backurl");
        if (!StringUtils.isEmpty(sessionId) && !StringUtils.isEmpty(backurl)){
            String token = RedisUtil.get(sessionId + "_token");
            //token校验值
            if (!StringUtils.isEmpty(token)){
                //回调子系统
                String redirectUrl = backurl;
                if (backurl.contains("?")){
                    redirectUrl += "&token=" + token;
                }else {
                    redirectUrl += "?token=" + token;
                }
                logger.info("认证中心帐号通过，带token回跳：{}", redirectUrl);
                return "redirect:" + redirectUrl;
            }
        }
        return "/sso/login";
    }

    /**
     * 登录页post
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String backurl = request.getParameter("backurl");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map result = new HashMap<>();
        String data = "";
        if (StringUtils.isEmpty(username)) {
            data = "帐号不能为空！";
            logger.info("帐号不能为空！");
            result.put("result", false);
            result.put("data", data);
            return result;
        }
        if (StringUtils.isEmpty(password)) {
            data = "密码不能为空！";
            logger.info("密码不能为空！");
            result.put("result", false);
            result.put("data", data);
            return result;
        }
        //分配单点登录sessionId，不使用session获取会话id,改为cookie，防止session丢失
        String sessionId = CookieUtil.getCookie(request, WAN_UPMS_SSO_SERVER_SESSION_ID);
        if (StringUtils.isEmpty(sessionId)){
            sessionId = request.getSession().getId();
            CookieUtil.setCookie(response, WAN_UPMS_SSO_SERVER_SESSION_ID, sessionId);
        }

        // 默认验证帐号密码正确，创建token
        String token = UUID.randomUUID().toString();
        // 全局会话sessionId
        RedisUtil.set(sessionId + "_token", token, TIMEOUT);
        // token校验值
        RedisUtil.set(token, token, TIMEOUT);
        //回调子系统
        String redirectUrl = backurl;
        if (backurl.contains("?")) {
            redirectUrl += "&token=" + token;
        } else {
            redirectUrl += "?token=" + token;
        }
        logger.info("认证中心帐号通过，带token回跳：{}", redirectUrl);
        result.put("result", true);
        result.put("data", redirectUrl);
        return result;
    }

    /**
     * 校验token
     * @param request
     * @return
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
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
        // 当前全局会话sessionId
        String sessionId = CookieUtil.getCookie(request, WAN_UPMS_SSO_SERVER_SESSION_ID);

        // 全局token
        String token = RedisUtil.get(sessionId + "_token");

        //清除全局会话
        RedisUtil.remove(sessionId + "_token");
        // 清除token校验值
        RedisUtil.remove(token);

        // 清除所有局部会话
        Jedis jedis = RedisUtil.getJedis();
        Set<String> subSessionIds = jedis.smembers(token + "_subSessionIds");
        for (String subSessionId : subSessionIds) {
            jedis.del(subSessionId + "_token");
            jedis.srem(token + "_subSessionIds", subSessionId);
        }
        logger.info("当前token={}，对应的注册系统还剩余：{}个", token, jedis.scard(token + "_subSessionIds"));

        // 跳回原地址
        String redirectUrl = request.getHeader("Referer");
        logger.info("跳回退出登录请求地址：{}", redirectUrl);
        return "redirect:" + redirectUrl;
    }
}
