package com.wan.upms.rpc.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;

public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // root application context
        if (null == contextRefreshedEvent.getApplicationContext().getParent()) {
            logger.info(">>>>> spring初始化完毕 <<<<<");
            // spring初始化完毕后，通过反射调用所有service的initMapper方法
            Map<String, Object> services = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(Service.class);
            logger.info("===== 开始初始化service的initMapper方法 =====");
            for (Object service : services.values()) {
                logger.info(">>>>> {}.initMapper()", service.getClass().getName());
                try {
                    Method initMapper = service.getClass().getMethod("initMapper");
                    initMapper.invoke(service);
                } catch (Exception e) {
                    logger.error("初始化service的initMapper方法异常", e);
                    e.printStackTrace();
                }
            }
            logger.info("===== 完成初始化service的initMapper方法 =====");
        }
    }
}