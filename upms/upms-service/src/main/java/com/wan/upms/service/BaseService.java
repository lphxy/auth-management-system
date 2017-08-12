package com.wan.upms.service;

/**
 * Created by w1992wishes on 2017/8/12.
 */
public interface BaseService<Mapper> {

    /**
     * 获取基本操作mapper
     * @return
     */
    Mapper getMapper();
}
