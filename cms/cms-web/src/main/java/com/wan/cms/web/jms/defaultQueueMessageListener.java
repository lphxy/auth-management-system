package com.wan.cms.web.jms;

import com.wan.cms.dao.model.User;
import com.wan.cms.service.service.UserService;
import net.sf.json.JSONObject;
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
    @Autowired
    UserService userService;

    public void onMessage(final Message message) {
        // 使用线程池多线程处理
        threadPoolTaskExecutor.execute(
                () -> {
                    TextMessage textMessage = (TextMessage)message;
                    try{
                        String text = textMessage.getText();
                        JSONObject json = JSONObject.fromObject(text);
                        User user = (User) JSONObject.toBean(json, User.class);
                        userService.getMapper().insertSelective(user);
                        logger.info("cms-mq接收到：{}", text);
                    }catch (Exception e){
                        logger.error("", e);
                    }
                }
        );
    }

}
