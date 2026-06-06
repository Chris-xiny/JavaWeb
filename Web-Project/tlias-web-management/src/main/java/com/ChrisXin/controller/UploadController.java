package com.ChrisXin.controller;

import com.ChrisXin.entity.Result;
import com.ChrisXin.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    /**
     * 本地磁盘存储
     */
    /*@PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接收参数:{},{},{}",name,age,file);
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        String extendsion=originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName= UUID.randomUUID().toString()+extendsion;

        //保存文件
        file.transferTo(new File("E:/images/"+newFileName));
        return Result.success();
    }*/

    @PostMapping("/upload")
    public Result upload(MultipartFile file)throws Exception{
        log.info("文件上传:{}",file.getOriginalFilename());
        //将文件交给AliyunOSS管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传OSS成功，url为:{}",url);
        return Result.success(url);
    }
}
