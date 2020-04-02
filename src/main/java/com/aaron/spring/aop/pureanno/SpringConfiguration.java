package com.aaron.spring.aop.pureanno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.aaron.spring.aop")
@EnableAspectJAutoProxy
public class SpringConfiguration {

    public SpringConfiguration(){
        System.out.println("spring 容器启动类初始化");
    }
}
