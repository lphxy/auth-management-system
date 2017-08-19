package com.wan.cms.job.controller;

import com.wan.cms.dao.model.CmsUser;
import com.wan.common.util.JmsUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    Logger logger = LoggerFactory.getLogger(ActiveMQController.class);

    @Autowired
    JmsTemplate jmsQueueTemplate;
    @Autowired
    Destination defaultQueueDestination;
    @Autowired

    @RequestMapping("/send")
    @ResponseBody
    public Object send() {
        long start = System.currentTimeMillis();
        CmsUser cmsUser = null;
        for (int i = 1; i <= 10000; i ++) {
            cmsUser = new CmsUser();
            cmsUser.setUsername("用户" + i);
            cmsUser.setUserId(i);
            JmsUtil.sendMessage(jmsQueueTemplate, defaultQueueDestination, JSONObject.fromObject(cmsUser).toString());
        }
        logger.info("发送消息消耗时间" + (System.currentTimeMillis() - start));
        return "success";
    }

}