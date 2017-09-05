package com.wan.common.base;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.InvalidSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器基类
 *
 * Created by w1992wishes on 2017/8/28.
 */
public abstract class BaseController {

    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    public static final String RESULT = "result";
    public static final String DATA = "data";
    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";

    /**
     * 统一异常处理
     * @param request
     * @param response
     * @param exception
     */
    @ExceptionHandler
    public String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        logger.error("统一异常处理：", exception);
        request.setAttribute("ex", exception);
        // shiro没有权限异常
        if (exception instanceof UnauthorizedException) {
            return "/403";
        }
        // shiro会话已过期异常
        if (exception instanceof InvalidSessionException) {
            return "/error";
        }
        return "/error";
    }

}
