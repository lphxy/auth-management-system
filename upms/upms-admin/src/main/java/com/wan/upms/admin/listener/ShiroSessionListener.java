package com.wan.upms.admin.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * Created by w1992wishes on 2017/9/5.
 */
public class ShiroSessionListener implements SessionListener {

    @Override
    public void onStart(Session session) {
        System.out.println("会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        System.out.println("会话停止：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println("会话过期：" + session.getId());
    }

}