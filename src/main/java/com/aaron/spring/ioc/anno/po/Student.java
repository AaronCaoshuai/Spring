package com.aaron.spring.ioc.anno.po;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Student {

    @Value("张三")//注入值
    // 可以使用@value("${name}") 取值方式取到Properties文件中的key即name对应的值
    private String name;

    @Autowired//=ref
    private Course course;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @PostConstruct //初始化
    public void init(){
        System.out.println("student init");
    }
    @PreDestroy //销毁方法
    public void destroy(){
        System.out.println("student destroy");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", course=" + course +
                '}';
    }
}
