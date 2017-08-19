package com.wan.cms.dao;

import com.wan.common.util.MybatisGeneratorConfigUtil;
import com.wan.common.util.PropertiesFileUtil;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by w1992wishes on 2017/8/18.
 */
public class Generator {
    private static Logger logger = LoggerFactory.getLogger(Generator.class);

    // 根据命名规范，只修改此常量值即可
    private static String MODULE_PREFIX_NAME = "cms";
    private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("jdbc").get("jdbc.driver");
    private static String JDBC_URL = PropertiesFileUtil.getInstance("jdbc").get("jdbc.url");
    private static String JDBC_USERNAME= PropertiesFileUtil.getInstance("jdbc").get("jdbc.username");
    private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("jdbc").get("jdbc.password");

    /**
     * 根据模板生成generatorConfig.xml文件
     * @param args
     */
    public static void main(String[] args) {
       // 更新generatorConfig.xml文件
        MybatisGeneratorConfigUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, MODULE_PREFIX_NAME);
        // 生成代码
        try {
            logger.info("start generator ...");
            List<String> warnings = new ArrayList<>();
            File configFile = new File(Generator.class.getResource("/generatorConfig.xml").getFile());
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            for (String warning : warnings) {
                logger.warn(warning);
            }
            logger.info("end generator!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
