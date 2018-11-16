package Aop基于AspectJ的AOP;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by lizhen on 2018/11/16.
 */
@Aspect
public class CatAspect {

    @Pointcut("execution(* Aop基于AspectJ的AOP.*.*(..))")
    public void test() {

    }

    @Before("test()")
    public void beforeTest() {
        System.out.println("==前置增强_cat");
    }

    @After("test()")
    public void afterTest() {
        System.out.println("==后置最终增强_cat");
    }
}
