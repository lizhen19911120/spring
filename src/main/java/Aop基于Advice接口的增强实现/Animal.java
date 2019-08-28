package Aop基于Advice接口的增强实现;

/**
 * Created by lizhen on 2018/11/15.
 */
public interface Animal {
    void sayHello(String name,int age);
    void run();
    void sayException(String name,int age);
}
