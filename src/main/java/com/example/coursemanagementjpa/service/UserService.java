package com.example.coursemanagementjpa.service;


import com.example.coursemanagementjpa.dto.CourseDto;
import com.example.coursemanagementjpa.entity.Course;
import com.example.coursemanagementjpa.exception.NotFoundException;
import com.example.coursemanagementjpa.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    CourseRepository courseRepository;


    // sử dụng dto
    public List<CourseDto> getCourses(String name, String type, String category) {
        return courseRepository.findCourse_Native(name, type,category);
    }

    // sử dụng câu lênhj query
    public List<Course> getAllCourse(String name, String type, String category) {
        return courseRepository.findCourseDemo1(name, type,category);
    }

    // 2. Xem thông tin của 1 khóa học cụ thể (thông tin bao gồm thông tin khóa học và nhân viên tư vấn)
    public Course findCourseById(Integer id) {
        Course course = courseRepository.findCoursesById(id).orElseThrow(() -> {
           throw new NotFoundException("Not Found Course with id = " + id);
        });
        return course;
    }
}
