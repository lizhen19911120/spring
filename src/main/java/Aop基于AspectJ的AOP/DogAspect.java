package Aop基于AspectJ的AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

/**
 * 切面类
 * Created by lizhen on 2018/11/15.
 */
@Aspect
public class DogAspect {

    /**
     * 定义切点
     * 例如：execution (* com.sample.service.impl..*.*(..)
     * 1、execution(): 表达式主体。
     * 2、第一个*号：表示返回类型，*号表示所有的类型。
     * 3、包名：表示需要拦截的包名，后面的两个点表示当前包和当前包的所有子包，
     * 即com.sample.service.impl包、子孙包下所有类的方法。
     * 4、第二个*号：表示类名，*号表示所有的类。
     * 5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个点表示任何参数。
     **/
    @Pointcut("execution(* Aop基于AspectJ的AOP.*.*(..))")
    public void test() {

    }

    @Before("test()")
    public void beforeTest() {
        System.out.println("==前置增强");
    }

    @After("test()")
    public void afterTest() {
        System.out.println("==后置最终增强");
    }

    @AfterThrowing("test()")
    public void afterThrowingTest() {
        System.out.println("==后置异常增强");
    }

    @AfterReturning("test()")
    public void afterReturningTest() {
        System.out.println("==后置返回增强");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint p) {
        System.out.println("==环绕增强开始");
        Object o = null;
        try {
            Signature signature = p.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            /**
             *
             * 调用methodSignature.getParameterNames()获得切点方法的参数时，如果代理是基于接口的可以获得到；但是如果代理是基于CGLIB的则不能获得到。
             *
             * depending on proxy type you can or can't have access to parameter names. If your bean implements interface, the JDK proxy will be created by spring, and in this kind of proxy MethodSignature.getParameterNames() is null. If your bean doesn't implement interface, CGLIB proxy is created, where MethodSignature.getParameterNames() is filled.

             If you can, you may switch to CGLIB proxy bean by removing bean interfaces and it should work.

             I'm struggling with the same now, and I can't remove interfaces. I figured out different solution for this. On the interface I can mark my parameters by some custom annot:

             interface MyInterface {
             void myMetod(@ParamName("foo") Object foo, @ParamName("bar") Object bar);
             }
             Now in AOP proxy I can get this information in:

             MethodSignature.getMethod().getParameterAnnotations()
             */
            String[] strings = methodSignature.getParameterNames();
            System.out.println(Arrays.toString(strings));
            o = p.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("==环绕增强结束");
        return o;
    }

    @DeclareParents(value = "Aop基于AspectJ的AOP.Dog", defaultImpl = IntroduceImpl.class)
    private IIntroduce iIntroduce;
}
