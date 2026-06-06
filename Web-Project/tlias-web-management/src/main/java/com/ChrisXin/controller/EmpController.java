package com.ChrisXin.controller;

import com.ChrisXin.entity.Emp;
import com.ChrisXin.entity.EmpQueryParam;
import com.ChrisXin.entity.PageResult;
import com.ChrisXin.entity.Result;
import com.ChrisXin.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 分页查询员工信息
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询:{}",empQueryParam);
        PageResult<Emp> pageResult=empService.page(empQueryParam);
        return Result.success(pageResult);
    }


    /**
     * 添加员工信息
     */
    @PostMapping
    public Result add(@RequestBody Emp emp){
        empService.add(emp);
        return Result.success();
    }

    /**
     * 删除员工信息-数组
     */
    /*@DeleteMapping
    public Result delete(Integer[] ids){
        log.info("删除员工:{}", Arrays.toString(ids));
        return Result.success();
    }*/

    /**
     * 删除员工信息-集合
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据员工id查询员工信息与工作经历信息
     */
    @GetMapping("/{id}")
    public Result search(@PathVariable Integer id){
        log.info("查询员工信息:{}",id);
        Emp emp=empService.search(id);
        return Result.success(emp);
    }

    /**
     * 修改员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        empService.update(emp);
        return Result.success();
    }

    /**
     * 查询所有员工信息
     */
    @GetMapping("/list")
    public Result list(){
        List<Emp> list=empService.list();
        return Result.success(list);
    }
}
