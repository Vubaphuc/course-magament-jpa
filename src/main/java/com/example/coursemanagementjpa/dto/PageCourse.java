package com.example.coursemanagementjpa.dto;

import com.example.coursemanagementjpa.entity.Course;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PageCourse {
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private int totalItems;
    private List<Course> data;
}
