package AopPointcut和Advisor以及静态普通方法名匹配切面;

import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * Created by lizhen on 2018/11/15.
 */
public class MyStaticPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

    private static String METHOD_NAME = "sayHello";

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return METHOD_NAME.equals(method.getName());
    }


}
