package com.wan.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring 上下文工具类
 *
 * Created by w1992wishes on 2017/7/1.
 */
public class SpringContextUtil implements ApplicationContextAware {

    public static SpringContextUtil springContextUtil = new SpringContextUtil();

    private static ApplicationContext context = null;

    public final static synchronized SpringContextUtil getInstance() {
        return springContextUtil;
    }

    private SpringContextUtil() {
        super();
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
