package 静态代理模式和JDK和CGLIB动态代理;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import 静态代理模式和JDK和CGLIB动态代理.CGLIB动态代理.Cat;
import 静态代理模式和JDK和CGLIB动态代理.CGLIB动态代理.CglibProxy;
import 静态代理模式和JDK和CGLIB动态代理.JDK动态代理.Animal;
import 静态代理模式和JDK和CGLIB动态代理.JDK动态代理.Dog;
import 静态代理模式和JDK和CGLIB动态代理.JDK动态代理.MyInvocationHandler;

/**
 * Created by lizhen on 2018/11/5.
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

    @Test
    public void test18() {
        // JDK动态代理
        MyInvocationHandler handler = new MyInvocationHandler(new Dog());
        Animal proxy = (Animal) handler.getProxy();
        proxy.say();
    }

    @Test
    public void test19() {
        // CGLIB动态代理
        Cat cat = (Cat) new CglibProxy().getInstance(Cat.class);
        cat.say();
    }
}
