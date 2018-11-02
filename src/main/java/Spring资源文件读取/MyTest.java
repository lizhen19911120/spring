package Spring资源文件读取;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Created by lizhen on 2018/10/29.
 */
public class MyTest {

    @Before
    public void before() {
        System.setProperty("spring.profiles.active", "dev");
        System.out.println("\n========测试方法开始=======\n");
    }

    @After
    public void after() {
        System.out.println("\n========测试方法结束=======\n");
    }


    /**
     * 去除path中的开头“/”作为路径信息，读取默认的classLoader，用classLoader来读取资源文件信息
     * 1、优先获取线程上下文类加载器
     * 2、没有则获取ClassUtils.class的类加载器
     * 3、没有则获取SystemClassLoader
     */
    @Test
    public void test14() {
        // 从资源文件夹下加载
        Resource resource = new ClassPathResource("/Spring资源文件读取/constructor.xml");
        /*
        或者
        Resource resource = new ClassPathResource("Spring资源文件读取/constructor.xml");
         */
        print(resource);

    }

    /**
     * 使用 MyTest 的类路径信息填充资源文件地址，然后读取默认的classLoader，用classLoader来读取资源文件信息
     * 1、获取 MyTest.class的类加载器
     * 2、没有则获取SystemClassLoader
     * 如果path参数有“/”，则如test14()测试方法加载资源文件
     */
    @Test
    public void test15() {
        // 使用类信息加载
        Resource resource = new ClassPathResource("constructor.xml", MyTest.class);
        /*
        或者
        Resource resource = new ClassPathResource("/Spring资源文件读取/constructor.xml", MyTest.class);
         */
        print(resource);

    }

    /**
     * 去除path中的开头“/”作为路径信息，选用MyTest类的classLoader，用classLoader来读取资源文件信息
     */
    @Test
    public void test16() {
        // 使用类加载器从资源文件夹下加载
        Resource resource = new ClassPathResource("Spring资源文件读取/constructor.xml", MyTest.class.getClassLoader());
         /*
        或者
        Resource resource = new ClassPathResource("/Spring资源文件读取/constructor.xml", MyTest.class.getClassLoader());
         */
        print(resource);
    }

    /**
     * 读取默认的classLoader，通过 ProtocolResolver解析path，配合classLoader获取Resource，用classLoader来读取资源文件信息
     * 1、优先获取线程上下文类加载器
     * 2、没有则获取ClassUtils.class的类加载器
     * 3、没有则获取SystemClassLoader
     * 可以自定义 ProtocolResolver 增加到 DefaultResourceLoader，实现对自定义地址协议的解析转换
     */
    @Test
    public void test17() {
        // 使用DefaultResourceLoader加载
        Resource resource = new DefaultResourceLoader().getResource("Spring资源文件读取/constructor.xml");
        /*
        或者
        Resource resource = new DefaultResourceLoader().getResource("/Spring资源文件读取/constructor.xml");
        Resource resource = new DefaultResourceLoader().getResource("classpath:Spring资源文件读取/constructor.xml");
        Resource resource = new DefaultResourceLoader().getResource("classpath:/Spring资源文件读取/constructor.xml");
         */
        print(resource);
    }

    // 打印资源文件内容
    public void print(Resource resource) {
        byte[] read = new byte[10000];
        try {
            resource.getInputStream().read(read, 0, read.length);
            System.out.println(new String(read));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
