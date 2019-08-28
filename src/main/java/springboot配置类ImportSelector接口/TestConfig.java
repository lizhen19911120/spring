package springboot配置类ImportSelector接口;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Li Zhen
 * @create 2019/8/21
 * @since 1.0.0
 */
@ComponentScan
@EnableTransactionManagement //开启事务管理
@EnableSpringStudy
@Configuration
public class TestConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        /**
         * 从AppConfig额外注入的配置类获得该bean
         */
        Dog dog = applicationContext.getBean(Dog.class);
        /**
         * 从CatLiteConfig额外注入的配置类获得该bean
         */
        Cat cat = applicationContext.getBean("cat",Cat.class);
        /**
         * 从InnnerConfig额外注入的配置类获得该bean
         */
        Cat cat1 = applicationContext.getBean("cat1",Cat.class);
        System.out.println(dog.getName());
        System.out.println(cat);
        System.out.println(cat1);

        /**
         * 配置类本身也当然会被注册为bean定义，也能获得
         */
        InnnerConfig bean = applicationContext.getBean(InnnerConfig.class);
        System.out.println(bean);

    }


    /**
     * 判断配置类有没有member class，即内部类时加入解析
     */
//    @Component
    class InnnerConfig{

        @Bean(value = "cat1")
        public Cat cat() {
            Cat cat = new Cat();
            cat.setName("Jerry");
            return cat;
        }

    }
}