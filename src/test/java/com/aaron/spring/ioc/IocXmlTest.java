package com.aaron.spring.ioc;

import com.aaron.spring.ioc.xml.di.Project;
import com.aaron.spring.ioc.xml.po.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocXmlTest {

    @Test
    public void testInitApplicationcontext(){
        //创建IOC容器 并进行初始化
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc.xml");
        System.out.println("======================================");

        //获取bean的实例,并验证bean的实例是否是单例模式的
        //默认情况下bean对象的scope是单例的
        Student student1 = (Student) context.getBean("student");
        Student student2 = (Student) context.getBean("student");
        System.out.println(student1 == student2);

       /* //容器销毁对象调用设置的destroy方法
        ((ClassPathXmlApplicationContext) context).close();*/

       //测试DI手动注入 构造函数注入和setter方法注入
        Project project = (Project)context.getBean("project");
        System.out.println(project);


    }
}
