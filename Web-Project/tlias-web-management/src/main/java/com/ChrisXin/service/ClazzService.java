package com.ChrisXin.service;

import com.ChrisXin.entity.Clazz;
import com.ChrisXin.entity.ClazzQueryParam;
import com.ChrisXin.entity.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 分页查询
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 根据id删除班级信息
     */
    void deleteById(Integer id);

    /**
     * 添加班级信息
     */
    void add(Clazz clazz);

    /**
     * 根据id查询班级信息
     */
    Clazz searchById(Integer id);

    /**
     * 修改班级信息
     */
    void update(Clazz clazz);

    /**
     * 查询所有班级信息
     */
    List<Clazz> findAll();
}
