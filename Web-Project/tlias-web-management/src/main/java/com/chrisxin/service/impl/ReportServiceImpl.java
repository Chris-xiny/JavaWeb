package com.chrisxin.service.impl;

import com.chrisxin.entity.JobOption;
import com.chrisxin.entity.StudentOption;
import com.chrisxin.mapper.EmpMapper;
import com.chrisxin.mapper.StudentMapper;
import com.chrisxin.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper StudentMapper;

    /**
     * 员工职位数量统计
     */
    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> empJobData = empMapper.getEmpJobData();
        List<Object> posList = empJobData.stream().map(mapData -> mapData.get("pos")).toList();
        List<Object> numList = empJobData.stream().map(mapData -> mapData.get("num")).toList();
        return new JobOption(posList,numList);
    }

    /**
     * 员工性别人数统计
     */
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.getEmpGenderData();
    }

    /**
     * 班级人数统计
     */
    @Override
    public StudentOption getStudentCountData() {
        List<Map<String,Object>> list= StudentMapper.getStudentCountData();
        List<Object> className = list.stream().map(mapData -> mapData.get("clazzName")).toList();
        List<Object> num = list.stream().map(mapData -> mapData.get("num")).toList();
        return new StudentOption(className,num);
    }

    /**
     * 学生学历人数统计
     */
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return StudentMapper.getStudentDegreeData();
    }


}
