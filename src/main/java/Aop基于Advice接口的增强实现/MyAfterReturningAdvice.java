package Aop基于Advice接口的增强实现;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by lizhen on 2018/11/15.
 */
public class MyAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("==后置增强");
        System.out.println("==方法名：" + method.getName());
        if (null != args && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("==第" + (i + 1) + "参数：" + args[i]);
            }
        }
        System.out.println("==目标类信息：" + target.toString());
    }
}
