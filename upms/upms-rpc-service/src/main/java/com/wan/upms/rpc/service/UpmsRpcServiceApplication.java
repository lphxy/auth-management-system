package com.wan.upms.rpc.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by w1992wishes on 2017/8/28.
 */
public class UpmsRpcServiceApplication {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        System.out.println(">>>>> upms-rpc-service 启动完成 <<<<<");
    }
}
