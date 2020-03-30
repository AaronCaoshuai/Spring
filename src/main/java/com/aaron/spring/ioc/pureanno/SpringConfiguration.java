package com.aaron.spring.ioc.pureanno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//spring3.0开始可以使用@Configuration定义配置类
//可以替换xml配置文件
@Configuration
//相当于xml中开启注解扫描
@ComponentScan(basePackages = "com.aaron.spring.ioc.pureanno")
//相当于<import>导入xml文件,来组合多个配置类,可以不再写@Configuration注解
//value:用来指定其他配置类的字节码文件
@Import({JdbcConfig.class})
public class SpringConfiguration {

    public SpringConfiguration() {
        System.out.println("spring 容器初始化");
    }
}
