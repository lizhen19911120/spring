package 使用SpringData设计Dao层.domain;

import com.sun.istack.internal.NotNull;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by lizhen on 2018/12/3.
 * Classroom的复合主键类
 * 该类实现 java.io.Serializable 接口，并重写 equals 和 hascode
 */
@Embeddable
/**
 * 将复合主键类的属性名和数据库表列名一一对应
 */
@AttributeOverrides( {
        @AttributeOverride(name = "building", column = @Column(name = "building")),
        @AttributeOverride(name = "roomNumber", column = @Column(name = "room_number")) })
public class ClassroomPK implements Serializable {

    @NotNull
    private String building;

    @NotNull
    private String roomNumber;

    public ClassroomPK() {
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassroomPK that = (ClassroomPK) o;

        if (building != null ? !building.equals(that.building) : that.building != null) return false;
        return roomNumber != null ? roomNumber.equals(that.roomNumber) : that.roomNumber == null;
    }

    @Override
    public int hashCode() {
        int result = building != null ? building.hashCode() : 0;
        result = 31 * result + (roomNumber != null ? roomNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClassroomPK{" +
                "building='" + building + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                '}';
    }
}
