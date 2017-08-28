package com.wan.cms.rpc.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 *
 * Created by w1992wishes on 2017/8/28.
 */
public class CmsRpcServiceApplication {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        System.out.println(">>>>> zheng-cms-rpc-service 启动完成 <<<<<");
    }
}
