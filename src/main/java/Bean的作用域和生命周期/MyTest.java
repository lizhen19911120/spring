package Bean的作用域和生命周期;

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
         applicationContext = new ClassPathXmlApplicationContext("Bean的作用域和生命周期/constructor.xml");
    }

    @After
    public void after() {
        System.out.println("\n========测试方法结束=======\n");
    }

    @Test
    public void test12() {
        // 生命周期测试
        LifeCycleBean myLifeCycleBean = applicationContext.getBean("myLifeCycleBean", LifeCycleBean.class);
        System.out.println("08-->bean可以被使用了, beanInfo: " + myLifeCycleBean.toString());
        ((ClassPathXmlApplicationContext) applicationContext).close();

    }



}
