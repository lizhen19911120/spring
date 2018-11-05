package 静态代理模式和JDK和CGLIB动态代理.CGLIB动态代理;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by lizhen on 2018/11/5.
 */
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    // 这里的目标类型为Object，则可以接受任意一种参数作为被代理类，实现了动态代理
    public Object getInstance(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        // 返回代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("==代理方法开始执行");
        Object result = methodProxy.invokeSuper(o, objects);
//        System.out.println(o.toString());
        System.out.println(method.getName());
        System.out.println(Arrays.toString(objects));
        System.out.println(methodProxy.getSuperName());
        System.out.println("==代理方法结束执行");
        return result;
    }
}
