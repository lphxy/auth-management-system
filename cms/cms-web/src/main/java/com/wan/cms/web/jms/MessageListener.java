package com.wan.cms.web.jms;

import com.wan.cms.dao.model.CmsUser;
import com.wan.cms.rpc.api.CmsUserService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.TextMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by w1992wishes on 2017/7/31.
 */
@Component
public class MessageListener extends MessageListenerAdapter {

    private static Logger logger = LoggerFactory.getLogger(DefaultMessageQueueListener.class);

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    CmsUserService userService;

    @JmsListener(containerFactory = "connectionFactory", destination = "defaultQueueDestination")
    public void processOrder(final Message message) {
        // 使用线程池多线程处理
        threadPoolTaskExecutor.execute(new Runnable() {
            public void run() {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    JSONObject json = JSONObject.fromObject(text);
                    CmsUser user = (CmsUser) JSONObject.toBean(json, CmsUser.class);
                    if (user.getUsername().equals("1")) {
                        logger.info("消费开始时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
                    }
                    if (user.getUsername().equals("1000")) {
                        logger.info("消费结束时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
                    }
                    userService.insertSelective(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
