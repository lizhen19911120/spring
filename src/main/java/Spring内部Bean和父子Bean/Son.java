package Spring内部Bean和父子Bean;

/**
 * 定义子bean,注意这里SonBean和ParentBean之间无继承关系,而是通过配置文件维护其父子关系
 * Created by lizhen on 2018/10/29.
 */
public class Son {
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

    public void sayHello() {
        System.out.println("nage: " + name + "， age: " + age);
    }

}
