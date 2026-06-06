package com.ChrisXin.mapper;

import com.ChrisXin.entity.Student;
import com.ChrisXin.entity.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /**
     * 分页查询
     */
    List<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 添加学生信息
     */
    void add(Student student);

    /**
     * 根据学生id查询学生信息
     */
    @Select("select * from student where id=#{id}")
    Student searchById(Integer id);

    /**
     * 修改学生信息
     */
    void update(Student student);

    /**
     * 删除学生信息
     */
    @Select("delete from student where id=#{id}")
    void deleteById(Integer id);

    /**
     * 修改学生违纪信息
     */
    @Update("update student set violation_count=violation_count+1,violation_score=violation_score+#{score} where id=#{id}")
    void updateViolation(Integer id, Short score);

    /**
     * 班级人数统计
     */
    List<Map<String,Object>> getStudentCountData();

    /**
     * 学生等级统计
     */
    List<Map<String, Object>> getStudentDegreeData();
}
