package AopPointcut和Advisor以及静态普通方法名匹配切面;

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
     * 除了设置Advice以外，进一步增加pointcut，即过滤对目标对象的具体类、具体方法名应用增强，这里实现只对Dog类且方法名为sayHello应用增强
     */
    @Test
    public void test24() {
        // 1、创建目标类、增强、切入点
        Animal animal = new Dog();
        MyMethodBeforeAdvice advice = new MyMethodBeforeAdvice();
        MyStaticPointcutAdvisor advisor = new MyStaticPointcutAdvisor();
        advisor.setClassFilter(Dog.class::isAssignableFrom);

        // 2、创建ProxyFactory并设置目标类、增强、切面
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(animal);
        // 为切面类提供增强
        advisor.setAdvice(advice);
        proxyFactory.addAdvisor(advisor);

        // 3、生成代理实例
        Dog proxyDog = (Dog) proxyFactory.getProxy();
        proxyDog.sayHelloDog();
        System.out.println("\n\n");
        proxyDog.sayHello();
    }

    @Test
    public void test25() {
        // 1、创建目标类、增强、切入点
        Animal animal = new Cat();
        MyMethodBeforeAdvice advice = new MyMethodBeforeAdvice();
        MyStaticPointcutAdvisor advisor = new MyStaticPointcutAdvisor();
        advisor.setClassFilter(Dog.class::isAssignableFrom);

        // 2、创建ProxyFactory并设置目标类、增强、切面
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(animal);
        // 为切面类提供增强
        advisor.setAdvice(advice);
        proxyFactory.addAdvisor(advisor);

        // 3、生成代理实例
        Cat proxyDog = (Cat) proxyFactory.getProxy();
        proxyDog.sayHelloCat();
        System.out.println("\n\n");
        proxyDog.sayHello();
    }
}
