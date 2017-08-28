package com.wan.common.plugin;

import com.wan.common.util.DESUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 支持加密配置文件插件
 *
 * Created by w1992wishes on 2017/8/28.
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private String[] propertyNames = {
            "master.jdbc.password", "slave.jdbc.password", "master.redis.password"
    };

    /**
     * 解密指定propertyName的加密属性值
     * @param propertyName
     * @param propertyValue
     * @return
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        for (String p : propertyNames) {
            if (p.equalsIgnoreCase(propertyName)) {
                return DESUtil.getDecryptString(propertyValue);
            }
        }
        return super.convertProperty(propertyName, propertyValue);
    }

}
