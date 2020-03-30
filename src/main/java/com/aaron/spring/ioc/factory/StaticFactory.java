package com.aaron.spring.ioc.factory;

/**
 * 静态工厂创建对象
 */
public class StaticFactory {

    public static UserService createUserService(){
        return new UserServiceImpl();
    }
}
