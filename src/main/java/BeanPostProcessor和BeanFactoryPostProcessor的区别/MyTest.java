package BeanPostProcessor和BeanFactoryPostProcessor的区别;

import Spring实例化Bean的三种方式.Dog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lizhen on 2018/10/29.
 */
public class MyTest {

    private ApplicationContext applicationContext;

    @Before
    public void initXmlBeanFactory() {
        System.setProperty("spring.profiles.active", "dev");
        System.out.println("\n========测试方法开始=======\n");
         applicationContext = new ClassPathXmlApplicationContext("BeanPostProcessor和BeanFactoryPostProcessor的区别/constructor.xml");
    }

    @After
    public void after() {
        System.out.println("\n========测试方法结束=======\n");
    }

    @Test
    public void test13() {
        // BeanPostProcessor和BeanFactoryPostProcessor的区别
        Dog dog1 = applicationContext.getBean("dog1", Dog.class);
        System.out.println("dog1: " + dog1.toString());

    }



}
