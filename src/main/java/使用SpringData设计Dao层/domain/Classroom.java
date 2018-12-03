package 使用SpringData设计Dao层.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lizhen on 2018/12/3.
 * 复合主键也可以采用定义一个POJO主键类，然后在实体类上添加 @IdClass(ClassroomPK.class)注解，并在复合的主键属性上都添加@Id注解实现
 */
@Entity
@Table(name = "classroom")
public class Classroom {

    /**
     * 或者使用@Id注解
     */
    @EmbeddedId
    private ClassroomPK id;

    private Integer capacity;

    public ClassroomPK getId() {
        return id;
    }

    public void setId(ClassroomPK id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", capacity=" + capacity +
                '}';
    }
}
