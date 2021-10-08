package com.feng.hospital_reservation_system.codeGenerator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.feng.hospital_reservation_system.codeGenerator.BasicConstant.*;

public class CodeGenerator {

    public static void main(String[] args) {
        new AutoGenerator()
                .setGlobalConfig(globalConfig())
                .setPackageInfo(packageConfig())
                .setStrategy(strategyConfig())
                .setDataSource(dataSourceConfig())
                .setCfg(injectionConfig())
                .execute();
    }

    public static GlobalConfig globalConfig() {
        return new GlobalConfig()
                .setBaseColumnList(true)
                .setSwagger2(true)
                .setBaseColumnList(true)
                .setControllerName(CONTROLLER_NAME)
                .setAuthor(AUTHOR_NAME)
                .setMapperName(MAPPER_NAME)
                .setServiceName(SERVICE_NAME)
                .setFileOverride(true)
                .setOpen(false)
                .setOutputDir(OUTPUT_DIR_PATH)
                .setIdType(IdType.ID_WORKER)
                .setBaseResultMap(true);
    }

    public static PackageConfig packageConfig() {
        return new PackageConfig()
                .setEntity(ENTITY_PACKAGE_PATH)
                .setController(CONTROLLER_PACKAGE_PATH)
                .setParent(PACKAGE_PARENT_PATH)
                .setService(SERVICE_PACKAGE_PATH)
                .setMapper(MAPPER_PACKAGE_PATH);
    }

    public static DataSourceConfig dataSourceConfig() {
        return new DataSourceConfig()
                .setDriverName(driverClassName)
                .setDbType(dbType)
                .setUrl(url)
                .setUsername(username)
                .setPassword(password);
    }

    public static StrategyConfig strategyConfig() {
        return new StrategyConfig()
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setInclude(TABLE_NAMES)
                .setChainModel(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setLogicDeleteFieldName(LOGIC_DELETED_NAME)
                .setVersionFieldName(VERSION_NAME)
                .setEntityTableFieldAnnotationEnable(true)
                .setTableFillList(Arrays.asList(
                        new TableFill(INSERT_TIME, FieldFill.INSERT),
                        new TableFill(UPDATE_TIME, FieldFill.INSERT_UPDATE)
                ));
    }

    public static InjectionConfig injectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
    }

}
