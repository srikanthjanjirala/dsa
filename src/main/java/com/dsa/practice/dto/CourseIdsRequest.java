package com.dsa.practice.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CourseIdsRequest {
    private Set<Long> courseIds;
}
