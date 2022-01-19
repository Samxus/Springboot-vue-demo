package com.xuanhan.vuedemo.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;

public class Generator {


    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/springboot_vue_demo?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=America/New_York", "root", "000000000")
                .globalConfig(builder -> {
                    builder.author("xuanhan") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("/Users/macintosh/Documents/Programming/SpringBoot/vue-project/springboot-vue-demo/backend/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.xuanhan.vuedome") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "com/xuanhan/vuedemo/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user"); // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
