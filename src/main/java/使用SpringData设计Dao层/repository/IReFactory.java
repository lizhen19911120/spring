package 使用SpringData设计Dao层.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by lizhen on 2018/12/3
 * 基本Repository的工厂类，这里使用扩展的IBaseDaoIml替代默认的 SimpleJpaRepository，获得加强的findByAuto()方法
 */
public class IReFactory<T, ID extends Serializable> extends JpaRepositoryFactory {
    /**
     * Creates a new {@link JpaRepositoryFactory}.
     *
     * @param entityManager must not be {@literal null}
     */
    public IReFactory(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        return new IBaseDaoIml<T,ID>((Class<T>)information.getDomainType(),entityManager);
    }


    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return IBaseDao.class;
    }
}
