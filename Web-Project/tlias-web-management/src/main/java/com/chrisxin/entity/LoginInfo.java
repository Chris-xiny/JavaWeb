package com.chrisxin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    private Integer id;//主键
    private String username;//用户名
    private String name;// 姓名
    private String token;//令牌

}
