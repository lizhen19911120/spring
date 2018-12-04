package 国际化.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by lizhen on 2018/12/4.
 */
public class ResourceBundleMessageSourceTest {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("国际化/application.xml");

        MessageSource ms = (MessageSource)ctx.getBean("myResource");

        Object[] params = {"John", new GregorianCalendar().getTime()};

        //获取格式化的国际化信息
        String str1 = ms.getMessage("greeting.common",params,Locale.US);
        String str2 = ms.getMessage("greeting.morning",params,Locale.CHINA);
        String str3 = ms.getMessage("greeting.afternoon",params,Locale.CHINA);
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);


        String str5 = ctx.getMessage("greeting.common",params,Locale.US);
        String str6 = ctx.getMessage("greeting.morning",params,Locale.CHINA);
        String str7 = ctx.getMessage("greeting.afternoon",params,Locale.CHINA);
        System.out.println(str5);
        System.out.println(str6);
        System.out.println(str7);


        MessageSource ms1 = ctx.getBean("myResource1", ReloadableResourceBundleMessageSource.class);

        for (int i = 0; i < 2; i++) {
            String str4 = ms1.getMessage("greeting.common",params, Locale.US);
            System.out.println(str4);
            try {
                Thread.currentThread().sleep(20000); //①模拟程序应用，在此期间，我们更改资源文件
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }


}
