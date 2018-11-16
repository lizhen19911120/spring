package Aop基于Advice接口的增强实现;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by lizhen on 2018/11/15.
 */
public class MyTest {

    @Before
    public void before() {
        System.out.println("\n========测试方法开始=======\n");
    }

    @After
    public void after() {
        System.out.println("\n========测试方法结束=======\n");
    }

    /**
     * JDK动态代理所用到的代理类在程序调用到代理类对象时才由JVM真正创建，JVM根据传进来的 业务实现类对象 以及 方法名 ，动态地创建了一个代理类的class文件并被字节码引擎执行，然后通过该代理类对象进行方法调用。我们需要做的，只需指定代理类的预处理、调用后操作即可。JDK的动态代理需要实现InvocationHandler接口，并重写invoke方法。
     * JDK动态代理的代理对象在创建时，需要使用业务实现类所实现的接口作为参数（因为在后面代理方法时需要根据接口内的方法名进行调用）。如果业务实现类是没有实现接口而是直接定义业务方法的话，就无法使用JDK动态代理了。并且，如果业务实现类中新增了接口中没有的方法，这些方法是无法被代理的（因为无法被调用）。如果没有接口定义又想使用动态代理的话，那么可以使用CGLIB动态代理。
     */
    @Test
    public void test20() {
        // 前置增强
        // 1、实例化bean和增强
        Animal dog = new Dog();
        MyMethodBeforeAdvice advice = new MyMethodBeforeAdvice();

        // 2、创建ProxyFactory并设置代理目标和增强
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(dog);
        proxyFactory.addAdvice(advice);

        // 3、生成代理实例
        Animal proxyDog = (Animal) proxyFactory.getProxy();
        proxyDog.sayHello("二哈", 3);
    }

    /**
     * CGLIB是针对类来实现代理的，原理是对指定的业务类生成一个子类，并覆盖其中业务方法实现代理。因为采用的是继承，所以不能对final修饰的类进行代理。 在使用的时候需要引入cglib和asm的jar包
     */
    @Test
    public void test21() {
        // 后置增强
        // 1、实例化bean和增强
        Animal dog = new Dog();
        MyAfterReturningAdvice advice = new MyAfterReturningAdvice();

        // 2、创建ProxyFactory并设置代理目标和增强
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(dog);
        proxyFactory.addAdvice(advice);

        // 3、生成代理实例
        Animal proxyDog = (Animal) proxyFactory.getProxy();
        proxyDog.sayHello("二哈", 3);
    }

    @Test
    public void test22() {
        // 异常增强
        // 1、实例化bean和增强
        Animal dog = new Dog();
        MyThrowsAdvice advice = new MyThrowsAdvice();

        // 2、创建ProxyFactory并设置代理目标和增强
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(dog);
        proxyFactory.addAdvice(advice);

        // 3、生成代理实例
        Animal proxyDog = (Animal) proxyFactory.getProxy();
        proxyDog.sayException("二哈", 3);
//        proxyDog.run();
    }

    @Test
    public void test23() {
        // 环绕增强
        // 1、实例化bean和增强
        Animal dog = new Dog();
        MyMethodInterceptor advice = new MyMethodInterceptor();

        // 2、创建ProxyFactory并设置代理目标和增强
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(dog);
        proxyFactory.addAdvice(advice);

        // 3、生成代理实例
        Animal proxyDog = (Animal) proxyFactory.getProxy();
        proxyDog.sayHello("二哈", 3);
    }
}
