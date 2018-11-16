package Aop基于AspectJ的AOP;

/**
 * Created by lizhen on 2018/11/15.
 */
public class Dog implements Animal  {
    @Override
    public void sayHello() {
        System.out.println("--被增强的方法sayHello()");
    }

}
