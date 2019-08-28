package springboot配置类ImportSelector接口;

import org.springframework.stereotype.Repository;

/**
 * 额外配置类（也是个lite配置类）
 *
 * 以下注解提供了和@Component一样的能力
 *
 * 判断配置类是否有@ComponentScans、@ComponentScan注解这一步时加入解析
 *
 * @author Li Zhen
 * @create 2019/8/21
 * @since 1.0.0
 */

//@Component
@Repository
//@Service
//@Controller
public class SecondConfig {

}