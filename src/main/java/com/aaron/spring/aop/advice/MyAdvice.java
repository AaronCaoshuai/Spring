package com.aaron.spring.aop.advice;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 通知增强类
 */
public class MyAdvice {

    //前置通知
    public void before(){
        System.out.println("前置通知");
    }
    //后置通知
    public void afterReturing(){
        System.out.println("后置通知");
    }
    //最终通知
    public void after(){
        System.out.println("最终通知");
    }
    //抛出异常通知
    public void afterThrowing(){
        System.out.println("抛出异常通知");
    }

    /**
     * 环绕通知 切入点
     * @param joinPoint
     */
    public void around(ProceedingJoinPoint joinPoint){
        System.out.println("环绕通知---前置通知");
        try{
            joinPoint.proceed();
            System.out.println("环绕通知---后置通知");
        }catch (Throwable e){
            System.out.println("环绕通知---异常通知");
            e.printStackTrace();
        }finally{
            System.out.println("环绕通知---最终通知");
        }
    }
}
