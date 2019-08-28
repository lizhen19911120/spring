package Aop基于Advice接口的增强实现;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by lizhen on 2018/11/15.
 */
public class MyMethodBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("==前置增强");
        System.out.println("==方法名：" + method.getName());
        if (null != objects && objects.length > 0) {
            for (int i = 0; i < objects.length; i++) {
                System.out.println("==第" + (i + 1) + "参数：" + objects[i]);
            }
        }
        System.out.println("==目标类信息：" + o.toString());
    }
}
