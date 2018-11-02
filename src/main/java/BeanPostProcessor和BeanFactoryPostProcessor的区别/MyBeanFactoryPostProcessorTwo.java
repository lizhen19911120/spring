package BeanPostProcessor和BeanFactoryPostProcessor的区别;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * Created by lizhen on 2018/10/30.
 */
public class MyBeanFactoryPostProcessorTwo implements BeanFactoryPostProcessor, Ordered {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor第" + getOrder() + "次被调动");
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
