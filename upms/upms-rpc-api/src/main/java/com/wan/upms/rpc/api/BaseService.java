package com.wan.upms.rpc.api;

public interface BaseService<Mapper> {

    /**
     * 获取基本操作mapper
     * @return
     */
    Mapper getMapper();
}
