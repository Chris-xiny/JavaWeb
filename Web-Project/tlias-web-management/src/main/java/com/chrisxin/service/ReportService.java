package com.chrisxin.service;

import com.chrisxin.entity.JobOption;

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
}
