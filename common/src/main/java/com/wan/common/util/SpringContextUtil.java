package com.wan.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 根据名称获取bean
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    /**
     * 根据类型获取bean
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        T t = null;
        Map<String, T> map = context.getBeansOfType(clazz);
        for (Map.Entry<String, T> entry : map.entrySet()) {
            t = entry.getValue();
        }
        return t;
    }
}
