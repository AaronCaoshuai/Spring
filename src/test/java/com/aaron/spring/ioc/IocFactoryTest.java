package com.aaron.spring.ioc;

import com.aaron.spring.ioc.factory.OrderService;
import com.aaron.spring.ioc.factory.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-ioc-factory.xml")
public class IocFactoryTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Test
    public void testFactory(){
        orderService.createOrder();
        userService.work();
    }

}
