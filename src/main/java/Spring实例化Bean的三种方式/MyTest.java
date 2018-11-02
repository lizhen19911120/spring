package Spring实例化Bean的三种方式;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by lizhen on 2018/10/29.
 */
public class MyTest {

    private XmlBeanFactory xmlBeanFactory;

    @Before
    public void initXmlBeanFactory() {
        System.setProperty("spring.profiles.active", "dev");
        System.out.println("\n========测试方法开始=======\n");
        xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("Spring实例化Bean的三种方式/constructor.xml"));
    }

    @After
    public void after() {
        System.out.println("\n========测试方法结束=======\n");
    }

    @Test
    public void test1() {
        // 默认构造器
        System.out.println("默认构造器");
        Dog dog1 = xmlBeanFactory.getBean("dog1", Dog.class);
        dog1.sayHello();
    }

    @Test
    public void test2() {
        // 指定构造器
        System.out.println("有参构造器");
//        Dog dog2 = xmlBeanFactory.getBean("dog2", Dog.class);
        /**
         * 覆盖xml中定义的dog2，获取一个新的dog2，替换缓存中的dog2
         */
        Dog dog2 = (Dog)xmlBeanFactory.getBean("dog2", "Tom", 100);
        dog2.sayHello();
    }

    @Test
    public void test3() {
        // 静态工厂
        System.out.println("静态工厂");
        Dog dog3 = xmlBeanFactory.getBean("dog3", Dog.class);
        dog3.sayHello();
    }

    @Test
    public void test4() {
        // 实例工厂
        System.out.println("实例工厂");
        Dog dog4 = xmlBeanFactory.getBean("dog4", Dog.class);
        dog4.sayHello();
    }
}
