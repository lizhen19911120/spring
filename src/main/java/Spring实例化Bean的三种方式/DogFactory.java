package Spring实例化Bean的三种方式;

/**
 * 实例工厂方法实例化
 * Created by lizhen on 2018/10/29.
 */
public class DogFactory {

    public Dog newInstance(String name, int age) {
        return new Dog(name, age);
    }
}
