package Spring内部Bean和父子Bean;

/**
 * 定义内部bean
 * Created by lizhen on 2018/10/29.
 */
public class Inner {
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
