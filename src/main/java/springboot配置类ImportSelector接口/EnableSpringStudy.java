package springboot配置类ImportSelector接口;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 提供注入其他配置类的能力
 * 如果@Import注解里面直接写配置类，则直接按配置类处理之；
 * 如果@Import注解里面写ImportSelector接口的实现类，则通过该接口的selectImports()方法获得的类进行递归处理，直到按配置类处理；
 * 如果@Import注解里面写ImportBeanDefinitionRegistrar接口的实现类，会通过该接口的registerBeanDefinitions()方法将额外的bean定义注册到上下文中；
 *
 * @author Li Zhen
 * @create 2019/8/21
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import(SpringStudySelector.class)
public @interface EnableSpringStudy {

}