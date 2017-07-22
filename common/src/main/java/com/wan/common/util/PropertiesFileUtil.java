package com.wan.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置文件读取工具
 * <p>
 * Created by w1992wishes on 2017/6/28.
 */
public class PropertiesFileUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertiesFileUtil.class);

    // 当打开多个资源文件时，缓存资源文件
    private static ConcurrentHashMap<String, PropertiesFileUtil> configMap = new ConcurrentHashMap<>();

    // 打开文件时间，判断超时使用
    private Date loadTime = null;

    // 资源文件
    private ResourceBundle resourceBundle = null;
    // 默认资源文件名称
    private static final String NAME = "config";

    //私有构造，获取单例
    private PropertiesFileUtil(String bundleFile) {
        this.loadTime = new Date();
        this.resourceBundle = ResourceBundle.getBundle(bundleFile);
    }

    public static PropertiesFileUtil getInstance(){
        return getInstance(NAME);
    }

    public static PropertiesFileUtil getInstance(String name) {
        PropertiesFileUtil conf =
                Optional.ofNullable(configMap.get(name))
                        .orElseGet(() -> {
                            PropertiesFileUtil newProUtil = new PropertiesFileUtil(name);
                            configMap.put(name, newProUtil);
                            return newProUtil;
                        });

        // 判断是否打开的资源文件是否超时1分钟
        if ((new Date().getTime() - conf.getLoadTime().getTime()) > 60*1000) {
            conf = new PropertiesFileUtil(name);
            configMap.put(name, conf);
        }

        return conf;
    }

    // 根据key读取value
    public String get(String key) {
        try {
            String value = resourceBundle.getString(key);
            return value;
        }catch (MissingResourceException e) {
            return "";
        }
    }

    // 根据key读取value(整形)
    public Integer getInt(String key) {
        try {
            String value = resourceBundle.getString(key);
            return Integer.parseInt(value);
        }catch (MissingResourceException e) {
            return null;
        }
    }

    public Date getLoadTime() {
        return loadTime;
    }
}
