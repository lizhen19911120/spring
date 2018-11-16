package Aop基于Schema的AOP;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

    @Test
    public void test26() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Aop基于Schema的AOP/aspect.xml");
//        Cat cat = ctx.getBean("cat", Cat.class); //CGLIB
        Animal cat = ctx.getBean("cat", Animal.class); //jdk
        cat.sayHello("美美", 3);
        System.out.println("---------------------------------------------");
        cat.run();
        System.out.println("---------------------------------------------");
        IIntroduce introduce = ctx.getBean("cat", IIntroduce.class);
        System.out.println(cat==introduce);//cat和introduce是同一个bean。
        introduce.sayIntroduce();
        System.out.println("---------------------------------------------");
        cat.sayException("丑丑");
    }

}
