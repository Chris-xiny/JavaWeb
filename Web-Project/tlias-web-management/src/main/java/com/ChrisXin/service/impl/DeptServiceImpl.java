package com.ChrisXin.service.impl;

import com.ChrisXin.entity.Dept;
import com.ChrisXin.exception.IsEmptyException;
import com.ChrisXin.mapper.DeptMapper;
import com.ChrisXin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public void deleteById(Integer id) {
        if(deptMapper.isEmpty(id)!=0){
            throw new IsEmptyException("该部门下有员工，不能删除");
        }else{
            deptMapper.deleteById(id);
        }
    }

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void insert(Dept dept) {
        //补全属性值
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept findById(Integer id) {
        Dept dept=deptMapper.findById(id);
        return dept;
    }

    @Override
    public void update(Dept dept) {
        //补全基础属性
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
