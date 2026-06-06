package com.ChrisXin.service;

import com.ChrisXin.entity.JobOption;
import com.ChrisXin.entity.OperateLog;
import com.ChrisXin.entity.PageResult;
import com.ChrisXin.entity.StudentOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 员工职位数量统计
     */
    JobOption getEmpJobData();


    /**
     * 员工性别人数统计
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 班级人数统计
     */
    StudentOption getStudentCountData();

    /**
     * 学生学历人数统计
     */
    List<Map<String, Object>> getStudentDegreeData();

    /**
     * 分页查询
     */
    PageResult<OperateLog> page(Integer page, Integer pageSize);
}
