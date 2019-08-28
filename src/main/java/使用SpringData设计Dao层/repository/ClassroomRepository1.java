package 使用SpringData设计Dao层.repository;

import org.springframework.stereotype.Repository;
import 使用SpringData设计Dao层.domain.Classroom;
import 使用SpringData设计Dao层.domain.ClassroomPK;

import java.util.List;

/**
 * Created by lizhen on 2018/12/3.
 */
@Repository
public interface ClassroomRepository1 extends IBaseDao<Classroom,ClassroomPK>{

    List<Classroom> findByCapacity(Integer capacity);
}
