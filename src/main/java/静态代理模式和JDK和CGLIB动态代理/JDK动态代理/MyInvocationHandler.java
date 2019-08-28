package 静态代理模式和JDK和CGLIB动态代理.JDK动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lizhen on 2018/11/5.
 */
public class MyInvocationHandler implements InvocationHandler {

    // 目标对象
    private Object target;

    /**
     * 构造方法
     * @param target 目标对象
     */
    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    /**
     * @param proxy  JDK动态生成的最终代理对象
     * @param method 调用真实对象的某个方法的Method对象
     * @param args   调用真实对象某个方法时接受的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("==代理方法开始执行");

        /**
         * 暴露代理给目标类，实现目标类对自己方法调用的代理
         */
        ((Dog)target).setProxy(getProxy());

        Object invoke = method.invoke(target, args);

        myMehtod();

        say();

        System.out.println("==代理方法结束执行");
        return invoke;
    }

    /**
     * 获取目标对象的代理对象
     * @return 代理对象
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 代理类自己的方法
     */
    void myMehtod(){
        System.out.println("代理类自己的方法");
    }

    /**
     * 覆盖say()
     */
    public void say() {
        System.out.println("I am a proxy dog...");
    }

}
