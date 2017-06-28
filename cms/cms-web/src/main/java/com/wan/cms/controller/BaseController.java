package com.wan.cms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * Created by w1992wishes on 2017/6/26.
 */
public class BaseController {
    public static final String RESULT = "result";
    public static final String DATA = "data";
    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";

    public Logger logger = LoggerFactory.getLogger(getClass());
}
