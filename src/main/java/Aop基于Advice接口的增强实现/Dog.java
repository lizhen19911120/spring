package Aop基于Advice接口的增强实现;

/**
 * Created by lizhen on 2018/11/15.
 */
public class Dog implements Animal {
    @Override
    public void sayHello(String name, int age) {
        System.out.println("==名字：" + name + " 年龄：" + age);
    }

    @Override
    public void run() {
        System.out.println("--------dog is running-------");
    }

    @Override
    public void sayException(String name, int age) {
        System.out.println("==名字：" + name + " 年龄：" + age);
        System.out.println("==抛出异常：" + 1 / 0);
    }
}
