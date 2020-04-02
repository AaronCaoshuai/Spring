package com.aaron.spring.aop.target;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void saveUser() {
        System.out.println("保存用户");
    }

    @Override
    public void updateUser() {
        System.out.println("修改用户");
    }

    @Override
    public void deleteUser() {
        System.out.println(1/0);
        System.out.println("删除用户");
    }
}
