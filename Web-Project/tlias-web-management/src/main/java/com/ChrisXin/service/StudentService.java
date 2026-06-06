package com.ChrisXin.service;

import com.ChrisXin.entity.PageResult;
import com.ChrisXin.entity.Student;
import com.ChrisXin.entity.StudentQueryParam;

public interface StudentService {

    /**
     * 分页查询
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 添加
     */
    void add(Student student);

    /**
     * 根据id查询
     */
    Student searchById(Integer id);

    /**
     * 修改
     */
    void update(Student student);

    /**
     * 删除
     */
    void deleteById(Integer id);

    void updateViolation(Integer id, Short score);
}
