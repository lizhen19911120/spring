package Spring实例化Bean的三种方式;

/**
 * 静态工厂实例化
 * Created by lizhen on 2018/10/29.
 */
public class DogStaticFactory {

    // 静态工厂方法
    public static Dog newInstance(String name, int age) {
        // 返回需要的Bean实例
        return new Dog(name, age);
    }
}
