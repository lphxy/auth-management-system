package com.wan.cms.web.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by wanqinfeng on 2017/7/10.
 */
@Component
public class TestTaskImpl implements TestTask {
    @Scheduled(cron = "0 0/1 * * * ?")
    @Override
    public void test() {
        System.out.println("Task");
    }
}