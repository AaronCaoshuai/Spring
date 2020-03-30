package com.aaron.spring.ioc.pureanno;

import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    public void saveUser() {
        System.out.println("保存用户");
    }
}
