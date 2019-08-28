package 使用SpringData设计Dao层.service;

import 使用SpringData设计Dao层.domain.Classroom;

import java.util.List;

/**
 * Created by lizhen on 2018/12/3.
 */
public interface ClassroomService {

    List<Classroom> findByCapacity(Integer capacity);
}
