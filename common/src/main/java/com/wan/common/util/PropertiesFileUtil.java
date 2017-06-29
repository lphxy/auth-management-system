package com.wan.common.util;

import java.util.Optional;
import java.util.ResourceBundle;

/**
 * 配置文件读取工具
 *
 * Created by w1992wishes on 2017/6/28.
 */
public class PropertiesFileUtil {
    private ResourceBundle resourceBundle = null;

    public PropertiesFileUtil(String bundleFile){
        resourceBundle = ResourceBundle.getBundle(bundleFile);
    }

    public String getValue(String key){
        return Optional.ofNullable(resourceBundle.getString(key)).orElse(null);
    }
}
