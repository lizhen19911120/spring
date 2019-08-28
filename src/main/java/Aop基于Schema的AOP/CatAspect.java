package Aop基于Schema的AOP;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 增强方法合集类
 * Created by lizhen on 2018/11/15.
 */
public class CatAspect {
    /**
     * 前置增强
     */
    public void beforeAdvice(String name, int age) {
        System.out.println("==前置增强，name：" + name + "，age：" + age);
    }

    /**
     * 后置异常增强,不能捕获方法抛出的异常。。。
     */
    public void afterExceptionAdvice(Exception ex,String name) {
        try {
            throw ex;
        }catch (Exception ex1){
            System.out.println("==后置异常增强，exception：" + ex1.getMessage());
        }


    }

    /**
     * 后置返回增强
     */
    public void afterReturningAdvice(String name, int age) {
        System.out.println("==后置返回增强，name：" + name + "，age：" + age);
    }

    /**
     * 后置最终增强
     */
    public void afterAdvice(String name, int age) {
        System.out.println("==后置最终增强，name：" + name + "，age：" + age);
    }

    /**
     * 环绕增强，可以捕获方法可能抛出的异常
     */
    public Object roundAdvice(ProceedingJoinPoint p, String name, int age) {
        System.out.println("==环绕增强开始，name：" + name + "，age：" + age);
        Object o = null;
        try {
            o = p.proceed();
            Object[] args = p.getArgs();
            if (null != args) {
                for (int i = 0; i < args.length; i++) {
                    System.out.println("==环绕增强参数值：" + args[i]);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("==环绕增强结束，name：" + name + "，age：" + age);
        return o;
    }
}
