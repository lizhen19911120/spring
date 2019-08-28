package springboot配置类ImportSelector接口;

/**
 * @author Li Zhen
 * @create 2019/8/21
 * @since 1.0.0
 */

public class Cat {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}