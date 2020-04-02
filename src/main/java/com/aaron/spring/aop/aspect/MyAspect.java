package com.aaron.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 使用spring注解编写的切面类
 */
@Component
@Aspect
public class MyAspect {

    private static final String pointCut = "execution(* *..*.*ServiceImpl.*(..))";

    //前置通知
    @Before(pointCut)
    public void before(){
        System.out.println("前置通知");
    }

    //后置通知
    @AfterReturning(value = "MyAspect.fn()")
    public void afterReturning(){
        System.out.println("后置通知");
    }

    //最终通知
    @After(pointCut)
    public void after(){
        System.out.println("最终通知");
    }

    //抛出异常通知
    @AfterThrowing(value = "MyAspect.fn()")
    public void afterThrowing(){
        System.out.println("抛出异常通知");
    }

    //环绕通知
    @Around(value = "MyAspect.fn()")
    public void around(ProceedingJoinPoint joinPoint){
        System.out.println("环绕--前置通知");
        try{
            joinPoint.proceed();//执行目标方法
            System.out.println("环绕--后置通知");
        }catch (Throwable e){
            System.out.println("环绕--抛出通知");
            e.printStackTrace();
        }finally {
            System.out.println("环绕--最终通知");
        }
    }

    @Pointcut("execution(* *..*.*ServiceImpl.*(..))")
    public void fn(){

    }
}
