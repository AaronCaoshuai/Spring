package com.aaron.spring.aop;

import com.aaron.spring.aop.target.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-aop.xml")
public class AopXmlTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveUserTest(){
        userService.saveUser();
    }

    @Test
    public void updateUserTest(){
        userService.updateUser();
    }

    @Test
    public void deleteUserTest(){
        userService.deleteUser();
    }
}
