package com.chrisxin.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class StudentQueryParam {
    private Integer page;
    private Integer pageSize;
    private String name;
    private Integer degree;
    private Integer classId;
}
