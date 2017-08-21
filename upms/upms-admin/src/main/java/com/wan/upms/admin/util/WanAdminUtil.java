package com.wan.upms.admin.util;

import com.wan.common.util.JarUtil;
import com.wan.common.util.PropertiesFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 启动解压admin-x.x.x.jar到resource目录
 *
 * Created by w1992wishes on 2017/8/12.
 */
public class WanAdminUtil implements InitializingBean, ServletContextAware {

    private static Logger logger = LoggerFactory.getLogger(WanAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        logger.info("=====start decompress admin.jar=====");
        String version = PropertiesFileUtil.getInstance().get("admin.version");
        logger.info("admin.jar version {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/admin-" + version + ".jar");
        logger.info("admin.jar path: {}", jarPath);
        String resources = servletContext.getRealPath("/resources/admin");
        logger.info("admin.jar decompress to: {}", resources);
        JarUtil.decompress(jarPath, resources);
        logger.info("=====decompress completely=====");
    }
}
