package Aop基于Schema的AOP;

/**
 * Created by lizhen on 2018/11/15.
 */
public class Cat implements Animal {
    @Override
    public void sayHello(String name, int age) {
        System.out.println("--调用被增强方法");
    }

    @Override
    public void run() {
        System.out.println("--------Cat is running-------");
    }

    @Override
    public void sayException(String name) {
        System.out.println("==抛出异常：" + 1 / 0);
    }
}
