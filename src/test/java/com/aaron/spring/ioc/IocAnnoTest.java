package com.aaron.spring.ioc;

import com.aaron.spring.ioc.anno.po.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-ioc-anno.xml")
public class IocAnnoTest {

    @Autowired
    private Student student;

    @Test
    public void testInitApplicationContext(){
        System.out.println(student);
        System.out.println(student.getCourse());
    }
}
