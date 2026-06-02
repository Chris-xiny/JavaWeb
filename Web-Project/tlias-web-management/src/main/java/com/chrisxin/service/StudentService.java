package com.chrisxin.service;

import com.chrisxin.entity.PageResult;
import com.chrisxin.entity.Student;
import com.chrisxin.entity.StudentQueryParam;

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
