package com.wan.upms.dao;

import com.wan.common.util.MybatisGeneratorUtil;
import com.wan.common.util.PropertiesFileUtil;

public class Generator {

	// 根据命名规范，只修改此常量值即可
	private static String MODULE_PREFIX_NAME = "upms";
	private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
	private static String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
	private static String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
	private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");

	/**
	 * 根据模板生成generatorConfig.xml文件
	 * @param args
	 */
	public static void main(String[] args) {
		MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, MODULE_PREFIX_NAME);
	}

}