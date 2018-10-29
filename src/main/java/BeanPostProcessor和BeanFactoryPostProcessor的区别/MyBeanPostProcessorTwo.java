package BeanPostProcessor和BeanFactoryPostProcessor的区别;

import Spring实例化Bean的三种方式.Dog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * 继承Ordered接口，设置不同BeanPostProcessor的调用顺序,这里第二次调用，修改覆盖name属性
 * Created by lizhen on 2018/10/29.
 */
public class MyBeanPostProcessorTwo implements BeanPostProcessor, Ordered {

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor第" + getOrder() + "次被调动");
        if (bean instanceof Dog) {
            ((Dog) bean).setName("弱弱");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
