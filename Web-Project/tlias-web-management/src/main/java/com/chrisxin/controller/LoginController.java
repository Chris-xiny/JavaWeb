package com.chrisxin.controller;

import com.chrisxin.entity.Emp;
import com.chrisxin.entity.LoginInfo;
import com.chrisxin.entity.Result;
import com.chrisxin.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;


    /**
     * 员工登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录:{}",emp);
        LoginInfo info=empService.login(emp);
        if(info==null){
            return Result.error("用户名或密码错误");
        }
        return Result.success(info);
    }
}
