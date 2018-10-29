package BeanFactory和FactoryBean的区别;

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
        xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("BeanFactory和FactoryBean的区别/constructor.xml"));
    }

    @After
    public void after() {
        System.out.println("\n========测试方法结束=======\n");
    }

    /**
     * FactoryBean简化xml配置，隐藏细节
     * 如果beanName不加&则获取到对应bean的实例；如果beanName加上"&"，则获取到BeanFactory本身的实例
     */
    @Test
    public void test10() {
        //FactoryBean简化配置测试
        System.out.println(xmlBeanFactory.getBean("student"));
        System.out.println(xmlBeanFactory.getBean("&student"));
    }

    /**
     * 返回不同Bean的实例
     */
    @Test
    public void test11() {
        //FactoryBean简单工厂测试
        Furniture furniture = xmlBeanFactory.getBean("furniture", Furniture.class);
        furniture.sayHello();
        Furniture furniture1 = xmlBeanFactory.getBean("furniture1", Furniture.class);
        furniture1.sayHello();
    }


}
