package springboot配置类ImportSelector接口;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 *
 * 配合@Import注解使用
 *
 * @author Li Zhen
 * @create 2019/8/21
 * @since 1.0.0
 */

public class SpringStudySelector implements ImportSelector, BeanFactoryAware {

    private BeanFactory beanFactory;

    /**
     * 通过实现BeanFactoryAware接口，让该类获得spring上下文BeanFactory。
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * 指定返回哪些额外的配置类给声明了@Import(SpringStudySelector.class)的原始配置类
     * （不一定是真正意义上的配置类，也可以只是普通的类，只是从这里返回的类会被当做配置类处理）,
     * 然后递归解析处理之。
     *
     * 特别的，可以发现当返回一个普通的Cat类时，也会被当做配置类处理加入到ConfigurationClassParser.configurationClasses中，
     * 最终这些configurationClasses 在 conConfigurationClassPostProcessor的processConfigBeanDefinitions()方法中的
     * this.reader.loadBeanDefinitions(configClasses);语句进行加载bean定义，也就是首先对配置类进行bean定义注册。
     * 因此，即使我们没有使用常规手段声明这个Cat bean，也能从上下文中获得。因为springboot自动将它注册进beanFactory中了。
     *
     *
     * 本案例是TestConfig作为入口配置类，通过@EnableSpringStudy注解导入此方法返回的
     * AppConfig（一个fullConfiguration）和CatLiteConfig（一个liteConfiguration，没有@Configuration注解，仅仅有@Bean方法）。
     * 从而获得了这2个配置类中的bean定义。
     *
     * @param annotationMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        /**
         * 获得TestConfig这个配置类上声明的注解，也就可以通过这些注解决定
         * 哪些额外的配置类需要注入到当前配置类中（这里我直接写死了返回AppConfig等）
         */
        annotationMetadata.getAnnotationTypes().forEach(System.out::println);
        /**
         * 获得当前beanFactory上下文有哪些bean定义，因为处理配置类流程对@ComponentScan注解
         * 的处理顺序在处理@import注解之前，
         * 而且通过Set<BeanDefinitionHolder> scannedBeanDefinitions =
         * this.componentScanParser.parse(componentScan, sourceClass.getMetadata().getClassName());
         * 直接将这些扫描到的配置类定义注册进上下文了！！！
         * 所以可以发现当前上下文中已经有了testConfig以及appConfig,secondConfig
         */
        System.out.println(beanFactory);
        /**
         * 可以直接返回普通类作为配置类，只不过没有真正配置类提供的功能罢了，也会被注册进上下文bean定义中
         */
//        return new String[]{AppConfig.class.getName(),Cat.class.getName()};
        return new String[]{AppConfig.class.getName(),CatLiteConfig.class.getName()};

    }


}