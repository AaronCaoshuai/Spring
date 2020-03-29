package com.aaron.spring.ioc.anno.po;

import org.springframework.stereotype.Component;

@Component
public class Course {

    private String name;

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

    public void init(){
        System.out.println("course init");
    }

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
