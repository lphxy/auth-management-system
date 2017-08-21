package com.wan.cms.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by w1992wishes on 2017/7/27.
 */
public class BaseController {
    public Logger logger = LoggerFactory.getLogger(getClass());

    public static final String RESULT = "result";
    public static final String DATA = "data";
    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";
}
