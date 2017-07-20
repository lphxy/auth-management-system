package com.wan.cms.mq.controller;

import com.wan.common.util.JmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Destination;

/**
 * Created by w1992wishes on 2017/7/20.
 */
@Controller
@RequestMapping("/activemq")
public class ActiveMQController{

    @Autowired
    @Qualifier("jmsQueueTemplate")
    JmsTemplate jmsTemplate;

    @Autowired
    Destination defaultQueueDestination;

    @RequestMapping("/send")
    @ResponseBody
    public Object send() {
        for (int i = 0; i < 1000; i ++) {
            JmsUtil.sendMessage(jmsTemplate, defaultQueueDestination, "消息" + i);
        }
        return "success";
    }

}