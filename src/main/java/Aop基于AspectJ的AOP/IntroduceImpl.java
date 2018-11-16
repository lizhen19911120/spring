package Aop基于AspectJ的AOP;

/**
 * 引入的增强接口实现
 * Created by lizhen on 2018/11/15.
 */
public class IntroduceImpl implements IIntroduce {
    @Override
    public void sayIntroduce() {
        System.out.println("--引入方法sayIntroduce()");
    }
}
