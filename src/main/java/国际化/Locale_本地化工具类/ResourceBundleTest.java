package 国际化.Locale_本地化工具类;

import org.springframework.util.ClassUtils;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by lizhen on 2018/12/4.
 */
public class ResourceBundleTest {

    public static void main(String[] args) {
        ResourceBundle rb1 = ResourceBundle.getBundle("国际化/resource", Locale.US);
        ResourceBundle rb2 = ResourceBundle.getBundle("国际化/resource", Locale.CHINA,ClassUtils.class.getClassLoader());
        System.out.println("us:"+rb1.getString("greeting.common"));
        System.out.println("cn:"+rb2.getString("greeting.common"));


        ResourceBundle rb3 = ResourceBundle.getBundle("国际化/fmt_resource",Locale.US);
        ResourceBundle rb4 = ResourceBundle.getBundle("国际化/fmt_resource",Locale.CHINA);
        Object[] params = {"John", new GregorianCalendar().getTime()};


        String str1 = new MessageFormat(rb3.getString("greeting.morning"),Locale.US).format(params);
        String str2 =new MessageFormat(rb4.getString("greeting.morning"),Locale.CHINA).format(params);
        String str3 =new MessageFormat(rb4.getString("greeting.afternoon"),Locale.CHINA).format(params);
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);

    }


}
