package com.example.coursemanagementjpa.entity.request;


import com.example.coursemanagementjpa.entity.Category;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpsertCourseRequest {
    private String name;
    private String description;
    private String type;
    private List<Integer> topics;
    private String thumbnail;
    private Integer userId;
}
