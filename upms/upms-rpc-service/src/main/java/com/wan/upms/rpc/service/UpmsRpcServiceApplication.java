package com.wan.upms.rpc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by w1992wishes on 2017/8/28.
 */
public class UpmsRpcServiceApplication {
    private static Logger logger = LoggerFactory.getLogger(UpmsRpcServiceApplication.class);

    public static void main(String[] args) {
        logger.info(">>>>> upms-rpc-service 正在启动 <<<<<");
        new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        logger.info(">>>>> upms-rpc-service 启动完成 <<<<<");
    }
}
