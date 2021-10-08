package com.feng.hospital_reservation_system.codeGenerator;

import com.baomidou.mybatisplus.annotation.DbType;

public class BasicConstant {

    /**
     * ------------------------------GlobalConfig--------------------------
     */
    public static String AUTHOR_NAME = "冯镔";
    public static String OUTPUT_DIR_PATH = System.getProperty("user.dir") + "/src/main/java";
    public static String CONTROLLER_NAME = "%sController";
    public static String SERVICE_NAME = "%sService";
    public static String MAPPER_NAME = "%sMapper";


    /**
     * ------------------------------PackageConfig--------------------------
     */
    public static String PACKAGE_PARENT_PATH = "com.feng.hospital_reservation_system";
    public static String ENTITY_PACKAGE_PATH = "pojo";
    public static String SERVICE_PACKAGE_PATH = "service";
    public static String CONTROLLER_PACKAGE_PATH = "controller";
    public static String MAPPER_PACKAGE_PATH = "mapper";

    /**
     * ------------------------------StrategyConfig--------------------------
     */
    public static String[] TABLE_NAMES = {
            "patient","doctor","orderRecord","admin","reservation"
    };
    public static String LOGIC_DELETED_NAME = "deleted";
    public static String VERSION_NAME = "version";
    public static String INSERT_TIME = "createTime";
    public static String UPDATE_TIME = "modifyTime";


    /**
     * ------------------------------DataSourceConfig--------------------------
     */
    public static String username = "root";
    public static String password = "fengbin";
    public static String url = "jdbc:mysql://localhost:3306/hospital_order_db?useAffectedRows=true&allowMultiQueries=true&characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
    public static String driverClassName = "com.mysql.cj.jdbc.Driver";
    public static DbType dbType = DbType.MYSQL;
}
