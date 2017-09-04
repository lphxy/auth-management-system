package com.wan.cms.rpc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 *
 * Created by w1992wishes on 2017/8/28.
 */
public class CmsRpcServiceApplication {
    private static Logger logger = LoggerFactory.getLogger(CmsRpcServiceApplication.class);

    public static void main(String[] args) {
        logger.info(">>>>> upms-rpc-service 正在启动 <<<<<");
        new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        logger.info(">>>>> upms-rpc-service 启动完成 <<<<<");
    }
}
