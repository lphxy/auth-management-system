package com.wan.api.rpc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by w1992wishes on 2017/9/9.
 */
public class ApiRpcServiceApplication {
    private static Logger logger = LoggerFactory.getLogger(ApiRpcServiceApplication.class);

    public static void main(String[] args) {
        logger.info(">>>>> api-rpc-service 正在启动 <<<<<");
        new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        logger.info(">>>>> api-rpc-service 启动完成 <<<<<");
    }
}
