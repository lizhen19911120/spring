package 使用SpringData设计Dao层;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import 使用SpringData设计Dao层.config.JpaConfig;
import 使用SpringData设计Dao层.domain.Classroom;
import 使用SpringData设计Dao层.domain.ClassroomPK;
import 使用SpringData设计Dao层.repository.ClassroomRepository;
import 使用SpringData设计Dao层.repository.ClassroomRepository1;
import 使用SpringData设计Dao层.service.ClassroomService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by lizhen on 2018/12/3.
 */
public class JpaTest {

    public static void main(String[] args) {

        /**
         * 使用spring data提供的domain-specific language (DSL)
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        ClassroomService classroomService = (ClassroomService)context.getBean("classroomServiceImpl");
        List<Classroom> byCapacity = classroomService.findByCapacity(100);
        System.out.println(Arrays.toString(byCapacity.toArray()));

        /**
         * 直接获得spring data创建的repository bean
         * 使用复合主键查询
         */
        ClassroomRepository classroomRepository = (ClassroomRepository)context.getBean("classroomRepository");
        ClassroomPK id = new ClassroomPK();
        id.setBuilding("lizhen");
        id.setRoomNumber("8808");
        Optional<Classroom> byId = classroomRepository.findById(id);
        if (byId.isPresent())
            System.out.println(byId.get());


        /**
         * 使用自定义接口提供复杂查询条件的repository
         * 起始页从0开始
         * 排序字段是POJO类的属性，这里本来写了复合主键里的building，导致报：PropertyReferenceException: No property building found for type Classroom
         */
        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.ASC, "id");
        ClassroomRepository1 classroomRepository1 = (ClassroomRepository1)context.getBean("classroomRepository1");
        Classroom classroom = new Classroom();
        classroom.setCapacity(100);
        Page<Classroom> page = classroomRepository1.findByAuto(classroom, pageable);

        List<Classroom> pageContent = page.getContent();
        System.out.println(Arrays.toString(pageContent.toArray()));

    }
}
