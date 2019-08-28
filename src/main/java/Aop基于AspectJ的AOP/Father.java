package Aop基于AspectJ的AOP;

/**
 * 定义父bean
 * Created by lizhen on 2018/10/29.
 */
public class Father {

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

}
