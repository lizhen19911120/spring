package Aop基于AspectJ的AOP;

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
    public void test27() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Aop基于AspectJ的AOP/aspect.xml");
        Animal dog = ctx.getBean("dog", Animal.class);
        dog.sayHello("汪汪");
        dog.run();
        System.out.println("---------------------------------------------");
        IIntroduce introduce = ctx.getBean("dog", IIntroduce.class);
        introduce.sayIntroduce();
        System.out.println("---------------------------------------------");
        Father father = ctx.getBean("father", Father.class);
        father.setAge(100);
    }

}
