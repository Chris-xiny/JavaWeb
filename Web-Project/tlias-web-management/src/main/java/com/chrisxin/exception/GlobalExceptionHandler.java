package com.chrisxin.exception;

import com.chrisxin.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("程序出错啦，请联系管理员",e);
        return Result.error("程序出错啦，请联系管理员");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出错啦，请联系管理员",e);
        String message=e.getMessage();
        int index=message.indexOf("Duplicate entry");
        String errMsg=message.substring(index);
        String[] s = errMsg.split(" ");
        return Result.error(s[2]+"已存在");
    }
}
