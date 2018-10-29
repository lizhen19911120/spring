package Spring注入集合属性;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * replace-method注入需实现MethodReplacer接口，并重写reimplement方法。
 * Created by lizhen on 2018/10/29.
 */
public class ReplaceDog implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {

        System.out.println("Hello, I am a white dog...");

        Arrays.stream(args).forEach(str -> System.out.println("参数:" + str));
        return obj;
    }
}
