package com.dsa.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String name;
    private Double salary;
    private String department;
//    private boolean flag;
    private long count;
}
