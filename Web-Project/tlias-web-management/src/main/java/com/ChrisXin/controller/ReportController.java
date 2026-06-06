package com.ChrisXin.controller;

import com.ChrisXin.entity.JobOption;
import com.ChrisXin.entity.Result;
import com.ChrisXin.entity.StudentOption;
import com.ChrisXin.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 员工职位数量人数
     */
    @GetMapping("/report/empJobData")
    public Result getEmpJobData(){
        log.info("开始统计员工职位人数");
        JobOption jobOption= reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 员工性别人数统计
     */
    @GetMapping("/report/empGenderData")
    public Result getEmpGenderData(){
        log.info("开始统计员工性别人数");
        List<Map<String,Object>> list= reportService.getEmpGenderData();
        return Result.success(list);
    }

    /**
     * 班级人数统计
     */
    @GetMapping("/report/studentCountData")
    public Result getStudentCountData(){
        log.info("开始统计学生数量");
        StudentOption studentOption = reportService.getStudentCountData();
        return Result.success(studentOption);
    }

    /**
     * 学生学历统计
     */
    @GetMapping("/report/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("开始统计学生学历数量");
        List<Map<String,Object>> list= reportService.getStudentDegreeData();
        return Result.success(list);
    }

    /**
     * 日志分页查询
     */
    @GetMapping("/log/page")
    public Result page(Integer page, Integer pageSize){
        return Result.success(reportService.page(page,pageSize));
    }


}
