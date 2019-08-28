package 使用SpringData设计Dao层.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import 使用SpringData设计Dao层.repository.ClassroomRepository;
import 使用SpringData设计Dao层.domain.Classroom;

import java.util.List;

/**
 * Created by lizhen on 2018/12/3.
 */
@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    ClassroomRepository classroomRepository;

    @Override
    public List<Classroom> findByCapacity(Integer capacity) {
        return classroomRepository.findByCapacity(capacity);
    }
}
