package com.chrisxin.service;

import com.chrisxin.entity.Emp;
import com.chrisxin.entity.EmpQueryParam;
import com.chrisxin.entity.LoginInfo;
import com.chrisxin.entity.PageResult;

import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);


    /**
     * 添加员工
     */
    void add(Emp emp);

    /**
     * 批量删除员工信息
     */
    void delete(List<Integer> ids);

    Emp search(Integer id);

    /**
     * 修改员工信息
     */
    void update(Emp emp);

    /**
     * 查询所有员工信息
     */
    List<Emp> list();

    /**
     * 员工登录
     */
    LoginInfo login(Emp emp);
}
