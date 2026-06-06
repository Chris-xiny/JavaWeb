package com.ChrisXin.controller;

import com.ChrisXin.anno.Log;
import com.ChrisXin.entity.Dept;
import com.ChrisXin.entity.Result;
import com.ChrisXin.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j//可以直接替换自己定义固定的log对象
@RequestMapping("/depts")//抽取类里注解的公共路径，类里的公共路径就可以去掉了
@RestController
public class DeptController {

    //private static final Logger log= LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;



    //查询所有部门
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list(){
        log.info("查询部门所有信息");
      List<Dept> deptList= deptService.findAll();
      return Result.success(deptList);


    }


    //根据id删除部门
    //方法一:形参定义HttpServletRequest对象接收，然后用getParameter()方法获得请求参数(最原始最繁杂)
    //方式二:形参名前加@RequestParam("id")进行参数绑定,values为前端请求参数名;一旦加了该注解，前端必须携带参数传递，因为required默认为true
    //方式三当前端请求参数名与形参名一致，形参不用加@RequestParam也可以直接接收到对应的参数(最推荐的方式)
    @Log
    @DeleteMapping
    public Result delete(Integer id){
        log.info("删除部门:{}",id);//"{}"为占位符,后面顺序接上参数
        deptService.deleteById(id);
        return Result.success();
    }


    //添加部门
    /*如何接收json格式数据?
    POST请求参数携带在body里，为json格式，用@RequestBody注解可以让json数据封装在形参的对象里，
    赋值给对象中对应的属性（需保证json格式的键值与对象属性名保持一致）
    */
    @Log
    @PostMapping
    public Result insert(@RequestBody Dept dept){
        log.info("新增部门:{}",dept);
        deptService.insert(dept);
        return Result.success();
    }

    /*根据id查询部门
    * 演示如何获取路径参数*/
    @GetMapping("/{id}")//{}路径占位符
    public Result findById(@PathVariable Integer id){
        log.info("查询部门:{}",id);
        Dept dept=deptService.findById(id);
        return Result.success(dept);
    }

    /*修改部门*/
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门:{}",dept);
        deptService.update(dept);
        return Result.success();
    }



}
