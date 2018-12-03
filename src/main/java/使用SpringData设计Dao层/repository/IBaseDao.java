package 使用SpringData设计Dao层.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by lizhen on 2018/12/3.
 * repository中间接口
 */
@NoRepositoryBean
public interface IBaseDao<T, ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {

    /**
     * 提供分页查询的泛型方法
     * @param model 实体类
     * @param pageable 分页类
     * @return 分页封装结果
     */
    Page<T> findByAuto(T model, Pageable pageable);

}
