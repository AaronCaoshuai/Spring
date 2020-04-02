package com.aaron.spring.aop;

import com.aaron.spring.aop.pureanno.SpringConfiguration;
import com.aaron.spring.aop.target.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopPureAnnoTest {

    ApplicationContext context = null;

    @Before
    public void init(){
        context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

    }

    @Test
    public void aopPureAnnoTest(){
        UserService userService = context.getBean(UserService.class);
        userService.saveUser();
        userService.updateUser();
        userService.deleteUser();
    }
}
