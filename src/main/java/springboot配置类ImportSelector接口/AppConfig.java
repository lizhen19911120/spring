package springboot配置类ImportSelector接口;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 一个full配置类
 * 判断配置类是否有@ComponentScans、@ComponentScan注解这一步时加入解析
 * 特别的，因为我在SpringStudySelector的selectImports()方法中也返回了这个配置类，即会发生重复注入解析的问题，
 *
 * ConfigurationClass existingClass = this.configurationClasses.get(configClass);
 * if (existingClass != null) {
 * if (configClass.isImported()) {
 * if (existingClass.isImported()) {
 * existingClass.mergeImportedBy(configClass);
 * }
 * // Otherwise ignore new imported config class; existing non-imported class overrides it.
 * return;
 * }
 *
 * 这段代码会进行处理，这里会当在处理配置类@Import注解时再次注入时会直接return
 *
 * @author Li Zhen
 * @create 2019/8/21
 * @since 1.0.0
 */
@Configuration
public class AppConfig {

    @Bean
    public Dog dog() {
        Dog dog = new Dog();
        dog.setName("旺财");
        dog.setAge(3);
        return dog;
    }


}