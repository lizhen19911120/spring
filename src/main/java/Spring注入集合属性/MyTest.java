package Spring注入集合属性;

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
        xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("Spring注入集合属性/constructor.xml"));
    }

    @After
    public void after() {
        System.out.println("\n========测试方法结束=======\n");
    }

    @Test
    public void test7() {
        // 注入集合属性
        Cat cat = xmlBeanFactory.getBean("cat", Cat.class);
        cat.sayListNames();
        cat.saySetNames();
        cat.sayArrayNames();
        cat.sayMapNames();
        cat.sayPropertiesNames();
    }

    /**
     * 未使用lookup-method注入时，通过Car的实例获取的Taxi实例是被缓存的（即使配置文件中Taxi的scope="prototype"，因为Car是singleton的，所以Taxi还是会被缓存）；
     * 而使用了lookup-method注入时，通过Car的实例获取的Taxi实例则是每次都是新建的，不是被缓存的，这也就达到了我们的目的。
     */
    @Test
    public void test8() {
        // 测试lookup-method注入
        Car car1 = xmlBeanFactory.getBean("car", Car.class);
        Car car2 = xmlBeanFactory.getBean("car", Car.class);

        System.out.println("Car:singleton,所以car1==car2为" + (car1 == car2));

        Taxi taxi1 = car1.getTaxi();
        Taxi taxi2 = car2.getTaxi();
        System.out.println("Taxi:prototype,Car:singleton,未使用lookup-method注入所以taxi1==taxi2为" + (taxi1 == taxi2));

        //注意:这里是通过createDog()方法获取
        Taxi taxi3 = car1.createTaxi();
        Taxi taxi4 = car2.createTaxi();
        System.out.println("Taxi:prototype,Car:singleton,使用了lookup-method注入所以taxi3==taxi4为" + (taxi3 == taxi4));

    }

    @Test
    public void test9() {
        //测试replace-method注入
        OriginalDog originalDog = xmlBeanFactory.getBean("originalDogReplaceMethod", OriginalDog.class);
        originalDog.sayHello();
        originalDog.sayHello("输出结果已经被替换了。。。");
    }



}
