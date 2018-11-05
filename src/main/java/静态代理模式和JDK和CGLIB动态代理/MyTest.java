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

    /**
     * JDK动态代理所用到的代理类在程序调用到代理类对象时才由JVM真正创建，JVM根据传进来的 业务实现类对象 以及 方法名 ，动态地创建了一个代理类的class文件并被字节码引擎执行，然后通过该代理类对象进行方法调用。我们需要做的，只需指定代理类的预处理、调用后操作即可。JDK的动态代理需要实现InvocationHandler接口，并重写invoke方法。
     * JDK动态代理的代理对象在创建时，需要使用业务实现类所实现的接口作为参数（因为在后面代理方法时需要根据接口内的方法名进行调用）。如果业务实现类是没有实现接口而是直接定义业务方法的话，就无法使用JDK动态代理了。并且，如果业务实现类中新增了接口中没有的方法，这些方法是无法被代理的（因为无法被调用）。如果没有接口定义又想使用动态代理的话，那么可以使用CGLIB动态代理。
     */
    @Test
    public void test18() {
        // JDK动态代理
        MyInvocationHandler handler = new MyInvocationHandler(new Dog());
        Animal proxy = (Animal) handler.getProxy();
        proxy.say();
    }

    /**
     * CGLIB是针对类来实现代理的，原理是对指定的业务类生成一个子类，并覆盖其中业务方法实现代理。因为采用的是继承，所以不能对final修饰的类进行代理。 在使用的时候需要引入cglib和asm的jar包
     */
    @Test
    public void test19() {
        // CGLIB动态代理
        Cat cat = (Cat) new CglibProxy().getInstance(Cat.class);
        cat.say();
    }
}
