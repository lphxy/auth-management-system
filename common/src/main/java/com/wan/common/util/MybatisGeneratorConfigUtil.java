package com.wan.common.util;

import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.corba.se.impl.util.Version.PROJECT_NAME;

/**
 * 代码生成类
 *
 * Created by w1992wishes on 2017/8/18.
 */
public class MybatisGeneratorConfigUtil {
    // 模板路径
    private static String VM_PATH = "common/src/main/resources/generatorConfig.vm";
    // 项目名称
    // 数据库名称
    private static String DATABASE_NAME = "ams";

    /**
     * 根据模板生成generatorConfig.xml文件
     * @param module_prefix_name
     */
    public static void generator(
            String jdbc_driver,
            String jdbc_url,
            String jdbc_username,
            String jdbc_password,
            String module_prefix_name) {
        String module_path = module_prefix_name + "/" +  module_prefix_name + "-dao/src/main/resources/generatorConfig.xml";
        String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + DATABASE_NAME + "' AND table_name LIKE '" + module_prefix_name + "_%';";
        System.out.println("========== 开始生成代码 ==========");
        try {
            VelocityContext context= new VelocityContext();
            List<Map<String, Object>> tables = new ArrayList<>();
            Map<String, Object> table = null;

            // 查询定制前缀项目的所有表
            JdbcUtil jdbcUtil = new JdbcUtil(jdbc_driver, jdbc_url, jdbc_username, jdbc_password);
            List<Map> result = jdbcUtil.selectByParams(sql, null);
            for (Map map : result) {
                System.out.println(map.get("TABLE_NAME"));
                table = new HashMap<>();
                table.put("table_name", map.get("TABLE_NAME"));
                table.put("model_name", StringUtil.lineToHump(ObjectUtils.toString(map.get("TABLE_NAME"))));
                tables.add(table);
            }
            jdbcUtil.release();

            context.put("tables", tables);
            context.put("generator_javaModelGenerator_targetPackage", "com." + PROJECT_NAME + "." + module_prefix_name + ".dao.model");
            context.put("generator_sqlMapGenerator_targetPackage", "com." + PROJECT_NAME + "." + module_prefix_name + ".dao.mapper");
            context.put("generator_javaClientGenerator_targetPackage", "com." + PROJECT_NAME + "." + module_prefix_name + ".dao.mapper");
            VelocityUtil.generate(VM_PATH, module_path, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("========== 结束生成代码 ==========");
    }
}
