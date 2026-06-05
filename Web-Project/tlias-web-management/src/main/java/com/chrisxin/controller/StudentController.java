package com.chrisxin.controller;

import com.chrisxin.entity.PageResult;
import com.chrisxin.entity.Result;
import com.chrisxin.entity.Student;
import com.chrisxin.entity.StudentQueryParam;
import com.chrisxin.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {


    @Autowired
    private StudentService studentService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("查询学生信息:{}",studentQueryParam);
        PageResult<Student> list = studentService.page(studentQueryParam);
        return Result.success(list);
    }

    /**
     * 添加学生信息
     */
    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("添加学生信息:{}",student);
        studentService.add(student);
        return Result.success();
    }

    /**
     * 根据学生id查询学生信息
     */
    @GetMapping("/{id}")
    public Result search(@PathVariable Integer id){
        log.info("查询学生信息:{}",id);
        Student student=studentService.searchById(id);
        return Result.success(student);
    }

    /**
     * 修改学生信息
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学生信息:{}",student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 删除学生信息
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除学生信息:{}",id);
        studentService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id,@PathVariable Short score){
        log.info("修改学生信息:{}",id);
        studentService.updateViolation(id,score);
        return Result.success();
    }

}
