package com.aaron.spring.ioc.xml.po;

public class Student {

    private String name;

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

    public void init(){
        System.out.println("student init");
    }

    public void destroy(){
        System.out.println("student destroy");
    }
}
