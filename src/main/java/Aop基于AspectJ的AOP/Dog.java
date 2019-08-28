package Aop基于AspectJ的AOP;

import org.springframework.aop.framework.AopContext;

/**
 * Created by lizhen on 2018/11/15.
 */
public class Dog implements Animal  {
    @Override
    public void sayHello(String words) {
        System.out.println("--被增强的方法sayHello()");
    }

    /**
     * expose-proxy="true" 返回代理对象调用sayHello()方法，run()和sayHello()都会被应用增强，不然只有run()应用增强
     * 类似于使用代理技术实现事务中的嵌套事务的概念
     */
    @Override
    public void run(){
        System.out.println("--被增强的方法run()");
        ((Animal)AopContext.currentProxy()).sayHello("喵喵");
    }

}
