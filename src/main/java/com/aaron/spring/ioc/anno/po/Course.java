package com.aaron.spring.ioc.anno.po;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Course {
    @Value("英语")
    private String name;
    @Value("123")
    private String num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @PostConstruct
    public void init(){
        System.out.println("course init");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("course destroy");
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
