package Spring内部Bean和父子Bean;

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
        xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("Spring内部Bean和父子Bean/constructor.xml"));
    }

    @After
    public void after() {
        System.out.println("\n========测试方法结束=======\n");
    }

    @Test
    public void test5() {
        // 内部bean
        Outer outer = xmlBeanFactory.getBean("outer", Outer.class);
        outer.sayHello();
    }

    @Test
    public void test6() {
        // 父子bean
        Son son = xmlBeanFactory.getBean("son", Son.class);
        son.sayHello();
    }

}
