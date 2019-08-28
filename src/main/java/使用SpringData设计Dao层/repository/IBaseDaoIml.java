package 使用SpringData设计Dao层.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by lizhen on 2018/12/3.
 */
public class IBaseDaoIml<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements IBaseDao<T, ID> {

    private final EntityManager entityManager;

    public IBaseDaoIml(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    /**
     * 自动根据传入的实体类的属性进行查询条件匹配，这里除了String属性使用模糊匹配，其他都使用精确匹配
     * @param model 实体类
     * @param pageable 分页类
     * @return 分页封装结果
     */
    @Override
    public Page<T> findByAuto(T model, Pageable pageable) {

        Class<T> tClass = (Class<T>)model.getClass();

        return findAll((root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            EntityType<T> entityType = entityManager.getMetamodel().entity(tClass);
            Set<Attribute<T, ?>> declaredAttributes = entityType.getDeclaredAttributes();
            for (Attribute<T, ?> attribute : declaredAttributes){
                Object value = ReflectionUtils.getField((Field) attribute.getJavaMember(), model);
                if(value != null){
                    if(attribute.getJavaType() == String.class){
                        list.add(criteriaBuilder.like(root.get(entityType.getDeclaredSingularAttribute(attribute.getName(),String.class)), "%"+value+"%"));
                    } else {
                        list.add(criteriaBuilder.equal(root.get(entityType.getDeclaredSingularAttribute(attribute.getName(),value.getClass())), value));
                    }
                }
            }
            Predicate[] p = new Predicate[list.size()];
            return list.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(list.toArray(p));
        },pageable);
    }
}
