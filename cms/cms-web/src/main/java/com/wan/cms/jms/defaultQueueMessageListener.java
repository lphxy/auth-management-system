package com.wan.cms.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by w1992wishes on 2017/7/20.
 */
public class defaultQueueMessageListener implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(defaultQueueMessageListener.class);

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public void onMessage(final Message message) {
        // 使用线程池多线程处理
        threadPoolTaskExecutor.execute(new Runnable() {
            public void run() {
                TextMessage textMessage = (TextMessage) message;
                try {
                    logger.info("cms-web接收到：{}", textMessage.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
