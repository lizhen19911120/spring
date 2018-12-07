package 静态代理模式和JDK和CGLIB动态代理.JDK动态代理;

/**
 * Created by lizhen on 2018/11/5.
 */
public class Dog implements Animal{

    /**
     * 传入代理类
     */
    private Object proxy;

    public Object getProxy() {
        return proxy;
    }

    public void setProxy(Object proxy) {
        this.proxy = proxy;
    }

    @Override
    public void say() {
        System.out.println("I am a dog...");
    }

    @Override
    public void run() {
        /**
         * 实现对say()方法的代理，不然子代理run()方法，原理和Aop基于AspectJ的AOP.Dog.run()一样
         */
        ((Animal)proxy).say();
//        say();
        System.out.println("I am running...");
    }
}
