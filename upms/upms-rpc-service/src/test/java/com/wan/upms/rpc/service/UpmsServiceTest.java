package com.wan.upms.rpc.service;

import com.wan.upms.dao.model.UpmsSystemExample;
import com.wan.upms.rpc.api.UpmsSystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by w1992wishes on 2017/9/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:applicationContext.xml",
        "classpath:applicationContext-jdbc.xml",
        "classpath:applicationContext-listener.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UpmsServiceTest {

    @Autowired
    private UpmsSystemService upmsSystemService;

    @Test
    public void index() {
        int count = upmsSystemService.countByExample(new UpmsSystemExample());
        System.out.println(count);
    }

}
