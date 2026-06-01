package com.chrisxin.service.impl;

import com.chrisxin.entity.JobOption;
import com.chrisxin.mapper.EmpMapper;
import com.chrisxin.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

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

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        List<Map<String, Object>> list=empMapper.getEmpGenderData();
        return list;
    }


}
