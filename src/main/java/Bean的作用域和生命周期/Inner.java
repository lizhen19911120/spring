package Bean的作用域和生命周期;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.BeanNameAware;

/**
 * 定义内部bean
 * Created by lizhen on 2018/10/29.
 */
public class Inner implements BeanNameAware{
    /** 姓名 **/
    private String name;

    /** 年龄 **/
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("-->BeanNameAware接口被调用了, 获取到的beanFactory中的beanName:" + name);
        System.out.println("-->获取到的原始beanName:" + BeanFactoryUtils.originalBeanName(name));
    }
}
