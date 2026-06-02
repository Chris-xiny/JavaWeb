package com.chrisxin.service.impl;

import com.chrisxin.entity.PageResult;
import com.chrisxin.entity.Student;
import com.chrisxin.entity.StudentQueryParam;
import com.chrisxin.mapper.StudentMapper;
import com.chrisxin.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查询
     */
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> list = studentMapper.page(studentQueryParam);
        Page<Student> p = (Page<Student>) list;
        return new PageResult<Student>(p.getTotal(), p.getResult());
    }

    /**
     * 添加
     */
    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        student.setViolationCount((short) 0);
        student.setViolationScore((short) 0);
        studentMapper.add(student);
    }

    /**
     * 根据id查询
     */
    @Override
    public Student searchById(Integer id) {
        return studentMapper.searchById(id);
    }

    /**
     * 修改
     */
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    /**
     * 删除
     */
    @Override
    public void deleteById(Integer id) {
        studentMapper.deleteById(id);
    }

    /**
     * 修改学生违纪信息
     */
    @Override
    public void updateViolation(Integer id, Short score) {
        studentMapper.updateViolation(id, score);
    }
}
