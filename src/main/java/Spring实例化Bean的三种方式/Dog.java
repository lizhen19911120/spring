package Spring实例化Bean的三种方式;

/**
 * Created by lizhen on 2018/10/29.
 */
public class Dog {

    /** 姓名 **/
    private String name;

    /** 年龄 **/
    private int age;

    /**
     * 默认构造函数
     */
    public Dog() {
    }

    /**
     * 构造函数
     * @param name 姓名
     * @param age  年龄
     */
    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayHello() {
        System.out.println("大家好, 我叫" + getName() + ", 我今年" + getAge() + "岁了");
    }

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
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
