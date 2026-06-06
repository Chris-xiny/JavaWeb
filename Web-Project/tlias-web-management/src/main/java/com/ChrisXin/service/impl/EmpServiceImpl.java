package com.ChrisXin.service.impl;

import com.ChrisXin.entity.*;
import com.ChrisXin.mapper.EmpExprMapper;
import com.ChrisXin.mapper.EmpMapper;
import com.ChrisXin.service.EmpService;
import com.ChrisXin.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    /*原始分页查询
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        Long total = empMapper.count();
        //计算起始页码
        Integer start = (page - 1) * pageSize;

        List<Emp> rows = empMapper.list(start, pageSize);
        PageResult<Emp> pageResult=new PageResult<>(total,rows);
        return pageResult;
    }*/

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //设置分页参数
        //PageHelper只会对紧跟着的第一条sql语句进行分页处理
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //执行查询
        List<Emp> rows = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) rows;
        //解析查询结果
        return new PageResult<>(p.getTotal(), p.getResult());
    }


    @Transactional(rollbackFor = Exception.class)//Spring的事务管理(默认出现运行时异常RuntimeException才会回滚)
    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //添加员工信息
        empMapper.add(emp);

        //添加员工经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            //遍历每一段员工经历为其添加员工id进行绑定
            for (EmpExpr empExpr : exprList) {
                empExpr.setEmpId(emp.getId());
            }
            empExprMapper.addBatch(exprList);
        }
    }

    /**
     * 删除员工信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        //删除员工信息
        empMapper.deleteByIds(ids);
        //删除员工经历
        empExprMapper.deleteByEmpId(ids);
    }


    /**
     * 查询员工信息与工作经历信息
     */
    @Override
    public Emp search(Integer id) {
        //查询员工基本信息
        Emp emp = empMapper.searchById(id);
        //查询员工工作经历
        List<EmpExpr> exprList = empExprMapper.searchByEmpId(id);
        emp.setExprList(exprList);
        return emp;
    }


    /**
     * 修改员工信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //先删除经历
        empExprMapper.deleteByEmpId(Arrays.asList(emp.getId()));
        //再添加经历
        List<EmpExpr> exprList = emp.getExprList();
        //为工作经历绑定员工id:empId
        if (!CollectionUtils.isEmpty(exprList)) {
            for (EmpExpr empExpr : exprList) {
                empExpr.setEmpId(emp.getId());
            }
        }
        empExprMapper.addBatch(exprList);
    }

    /**
     * 查询所有员工信息
     */
    @Override
    public List<Emp> list() {
        return empMapper.findAll();
    }

    /**
     * 员工登录
     */
    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.loginByUsernameAndPassword(emp);
        if (e != null) {
            log.info("员工登录成功:{}", e);
            //生成Jwt令牌
            Map<String, Object> claims=new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String token = JwtUtils.generateToken(claims);
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), token);
        }

        return null;
    }
}
