package com.ChrisXin.entity;

import lombok.Data;

@Data
public class StudentQueryParam {
    private Integer page;
    private Integer pageSize;
    private String name;
    private Integer degree;
    private Integer classId;
}
