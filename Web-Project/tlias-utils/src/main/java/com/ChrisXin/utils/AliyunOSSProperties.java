package com.ChrisXin.utils;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data//提供set方法供@ConfigurationProperties设置属性值，get方法供@Component注入
@Component//交给IOC管理用于注入
@ConfigurationProperties(prefix = "aliyun.oss")//profix指定yml中对应属性的前缀
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;
}
