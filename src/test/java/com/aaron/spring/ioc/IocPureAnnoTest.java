package com.aaron.spring.ioc;

import com.aaron.spring.ioc.pureanno.SpringConfiguration;
import com.aaron.spring.ioc.pureanno.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IocPureAnnoTest {

    @Test
    public void testPureAnno(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserService userService = context.getBean(UserService.class);
        userService.saveUser();
    }
}
