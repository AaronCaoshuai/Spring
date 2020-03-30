package com.aaron.spring.ioc.factory;

/**
 * 实例工厂
 */
public class InstanceFactory {

    public OrderService createOrderService(){
        return new OrderServiceImpl();
    }
}
