package com.chrisxin.controller;

import com.chrisxin.entity.JobOption;
import com.chrisxin.entity.Result;
import com.chrisxin.entity.StudentOption;
import com.chrisxin.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 员工职位数量人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("开始统计员工职位人数");
        JobOption jobOption= reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 员工性别人数统计
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("开始统计员工性别人数");
        List<Map<String,Object>> list= reportService.getEmpGenderData();
        return Result.success(list);
    }

    /**
     * 班级人数统计
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("开始统计学生数量");
        StudentOption studentOption = reportService.getStudentCountData();
        return Result.success(studentOption);
    }

    /**
     * 学生学历统计
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("开始统计学生学历数量");
        List<Map<String,Object>> list= reportService.getStudentDegreeData();
        return Result.success(list);
    }
}
