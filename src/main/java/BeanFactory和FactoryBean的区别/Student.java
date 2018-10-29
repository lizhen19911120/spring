package BeanFactory和FactoryBean的区别;

/**
 * 通过FactoryBean获取bean，可以简化xml配置，隐藏bean的配置细节
 * Created by lizhen on 2018/10/29.
 */
public class Student {

    /** 姓名 */
    private String name;
    /** 年龄 */
    private int age;
    /** 班级名称 */
    private String className;

    // ...可能能会有更多的属性

    public Student() {
    }

    public Student(String name, int age, String className) {
        this.name = name;
        this.age = age;
        this.className = className;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", age=" + age + ", className='" + className + '\'' + '}';
    }

}
