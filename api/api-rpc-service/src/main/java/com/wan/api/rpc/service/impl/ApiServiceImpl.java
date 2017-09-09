package com.wan.api.rpc.service.impl;

import com.wan.cms.rpc.api.*;
import com.wan.upms.rpc.api.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实现ApiService接口
 *
 * Created by w1992wishes on 2017/9/9.
 */
public class ApiServiceImpl {
    @Autowired
    private UpmsSystemService upmsSystemService;

    @Autowired
    private UpmsOrganizationService upmsOrganizationService;

    @Autowired
    private UpmsUserService upmsUserService;

    @Autowired
    private UpmsRoleService upmsRoleService;

    @Autowired
    private UpmsPermissionService upmsPermissionService;

    @Autowired
    private UpmsApiService upmsApiService;

    @Autowired
    private CmsArticleService cmsArticleService;

    @Autowired
    private CmsCategoryService cmsCategoryService;

    @Autowired
    private CmsCommentService cmsCommentService;

    @Autowired
    private CmsTagService cmsTagService;

    @Autowired
    private CmsUserService userService;
}
