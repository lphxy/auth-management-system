package com.wan.cms.service;

/**
 * Created by w1992wishes on 2017/6/26.
 */
public interface BaseService<Mapper> {

    /**
     * 获取基本操作mapper
     * @return
     */
    Mapper getMapper();

}
