package com.ChrisXin.controller;

import com.ChrisXin.entity.Clazz;
import com.ChrisXin.entity.ClazzQueryParam;
import com.ChrisXin.entity.PageResult;
import com.ChrisXin.entity.Result;
import com.ChrisXin.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 分页查询班级信息
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("查询参数为{}所有班级信息",clazzQueryParam);
        PageResult<Clazz> pageResult= clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 根据id删除班级信息
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("删除班级:{}",id);
        clazzService.deleteById(id);
        return Result.success();
    }

    /**
     * 添加班级信息
     */
    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("添加班级信息:{}",clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    /**
     * 根据id查询班级信息
     */
    @GetMapping("/{id}")
    public Result searchById(@PathVariable Integer id){
        log.info("查询班级:{}",id);
        Clazz clazz= clazzService.searchById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级信息
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 查询所有班级信息
     */
    @GetMapping("/list")
    public Result findAll(){
        return Result.success(clazzService.findAll());
    }

}
