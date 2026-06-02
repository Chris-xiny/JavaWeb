package com.chrisxin.service.impl;

import com.chrisxin.entity.Clazz;
import com.chrisxin.entity.ClazzQueryParam;
import com.chrisxin.entity.PageResult;
import com.chrisxin.exception.IsEmptyException;
import com.chrisxin.mapper.ClazzMapper;
import com.chrisxin.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 分页查询
     */
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        List<Clazz> rows = clazzMapper.page(clazzQueryParam);
        Page<Clazz> p = (Page<Clazz>) rows;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    /**
     * 根据id删除班级信息
     */
    @Override
    public void deleteById(Integer id) throws IsEmptyException {
        if(clazzMapper.isEmpty(id)!=0){
            throw new IsEmptyException("班级下存在学员!");
        }else {
            clazzMapper.deleteById(id);
        }
    }

    /**
     * 添加班级信息
     */
    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }

    /**
     * 根据id查询班级信息
     */
    @Override
    public Clazz searchById(Integer id) {
        return clazzMapper.searchById(id);
    }

    /**
     * 修改班级信息
     */
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    /**
     * 查询所有班级信息
     */
    @Override
    public List<Clazz> findAll() {
        return clazzMapper.list();
    }
}
