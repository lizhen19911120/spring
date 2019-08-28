package springboot配置类ImportSelector接口;

import org.springframework.context.annotation.Bean;

/**
 * 一个lite配置类
 * 因为我在SpringStudySelector的selectImports()方法中也返回了这个配置类，所以
 * 判断配置类是否有@Import注解这一步时加入解析
 *
 * @author Li Zhen
 * @create 2019/8/21
 * @since 1.0.0
 */

public class CatLiteConfig {

    @Bean
    public Cat cat() {
        Cat cat = new Cat();
        cat.setName("Tom");
        return cat;
    }
}