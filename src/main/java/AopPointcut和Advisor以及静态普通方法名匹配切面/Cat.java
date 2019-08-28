package AopPointcut和Advisor以及静态普通方法名匹配切面;

/**
 * Created by lizhen on 2018/11/15.
 */
public class Cat implements Animal {
    @Override
    public void sayHello() {
        System.out.println("我是Cat类的sayHello方法。。。");
    }

    public void sayHelloCat() {
        System.out.println("我是一只猫。。。");
    }
}
