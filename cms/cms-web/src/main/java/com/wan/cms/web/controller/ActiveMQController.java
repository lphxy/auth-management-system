package com.wan.cms.web.controller;

import com.wan.cms.dao.model.CmsUser;
import com.wan.common.util.JmsUtil;
import net.sf.json.JSONObject;
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
public class ActiveMQController extends BaseController {

    @Autowired
    JmsTemplate jmsQueueTemplate;

    @Autowired
    Destination defaultQueueDestination;

    @RequestMapping("/send")
    @ResponseBody
    public Object send() {
        long start = System.currentTimeMillis();
        CmsUser user = null;
        for (int i = 1; i <= 10000; i ++) {
            user = new CmsUser();
            user.setUsername(i + "");
            user.setUserId(i);
            JmsUtil.sendMessage(jmsQueueTemplate, defaultQueueDestination, JSONObject.fromObject(user).toString());
        }
        logger.info("发送消息消耗时间" + (System.currentTimeMillis() - start));
        return "success";
    }

}