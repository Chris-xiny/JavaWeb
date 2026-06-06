package com.ChrisXin.service.impl;

import com.ChrisXin.entity.JobOption;
import com.ChrisXin.entity.OperateLog;
import com.ChrisXin.entity.PageResult;
import com.ChrisXin.entity.StudentOption;
import com.ChrisXin.mapper.EmpMapper;
import com.ChrisXin.mapper.OperateLogMapper;
import com.ChrisXin.mapper.StudentMapper;
import com.ChrisXin.service.ReportService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
    private StudentMapper studentMapper;

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 员工职位数量统计
     */
    @Override

    public JobOption getEmpJobData() {
        List<Map<String, Object>> empJobData = empMapper.getEmpJobData();
        List<Object> posList = empJobData.stream().map(mapData -> mapData.get("pos")).toList();
        List<Object> numList = empJobData.stream().map(mapData -> mapData.get("num")).toList();
        return new JobOption(posList, numList);
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
        List<Map<String, Object>> list = studentMapper.getStudentCountData();
        List<Object> className = list.stream().map(mapData -> mapData.get("clazzName")).toList();
        List<Object> num = list.stream().map(mapData -> mapData.get("num")).toList();
        return new StudentOption(className, num);
    }

    /**
     * 学生学历人数统计
     */
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.getStudentDegreeData();
    }

    /**
     * 日志分页查询
     */
    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<OperateLog> rows = operateLogMapper.list();
        Page<OperateLog> p = (Page<OperateLog>) rows;
        return new PageResult<>(p.getTotal(), p.getResult());
    }


}
