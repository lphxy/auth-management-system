package com.wan.cms.admin.jms;

import com.wan.cms.dao.model.CmsUser;
import com.wan.cms.rpc.api.CmsUserService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by w1992wishes on 2017/7/20.
 */
public class DefaultMessageQueueListener implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(DefaultMessageQueueListener.class);

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    CmsUserService userService;

    public void onMessage(final Message message) {
        // 使用线程池多线程处理
        threadPoolTaskExecutor.execute(
                () -> {
                    TextMessage textMessage = (TextMessage)message;
                    try{
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
                        logger.info("cms-mq接收到：{}", text);
                    }catch (Exception e){
                        logger.error("", e);
                    }
                }
        );
    }

}
