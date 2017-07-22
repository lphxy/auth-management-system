package com.wan.cms.mq.controller;

import com.wan.common.util.JmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.jms.Destination;

/**
 * Created by w1992wishes on 2017/7/20.
 */
@Controller
@RequestMapping("/activemq")
public class ActiveMQController{

    Logger logger = LoggerFactory.getLogger(ActiveMQController.class);

    @Autowired
    @Qualifier("jmsQueueTemplate")
    JmsTemplate jmsTemplate;
    @Autowired
    Destination defaultQueueDestination;
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @RequestMapping("/send1")
    @ResponseBody
    public Object send1() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i ++) {
            JmsUtil.sendMessage(jmsTemplate, defaultQueueDestination, "消息" + i);
        }
        long end = System.currentTimeMillis();
        logger.info("发送消息消耗时间" + (end -start));
        return "success";
    }

    @RequestMapping("/send2")
    @ResponseBody
    public Object send2() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i ++) {
            long time = System.currentTimeMillis();
            threadPoolTaskExecutor.execute(() -> JmsUtil.sendMessage(jmsTemplate, defaultQueueDestination, "消息" + time));
        }
        long end = System.currentTimeMillis();
        logger.info("发送消息消耗时间" + (end -start));
        return "success";
    }

}