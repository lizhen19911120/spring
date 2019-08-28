package AopPointcut和Advisor以及静态普通方法名匹配切面;

/**
 * Created by lizhen on 2018/11/15.
 */
public class Dog implements Animal {
    @Override
    public void sayHello() {
        System.out.println("我是Dog类的sayHello方法。。。");
    }

    public void sayHelloDog() {
        System.out.println("我是一只狗。。。");
    }
}
