package com.example.coursemanagementjpa.controller;

import com.example.coursemanagementjpa.dto.CourseDto;
import com.example.coursemanagementjpa.entity.Course;
import com.example.coursemanagementjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    // sử dụng dto để xử lý
    @GetMapping("/courses")
    public List<CourseDto> getCourses(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) String type,
                                      @RequestParam(required = false) String category
    ) {
        return userService.getCourses(name, type, category);
    }

    @GetMapping("example")
    public List<Course> getAllCourse(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String type,
                                     @RequestParam(required = false) String category) {
        return userService.getAllCourse(name, type, category);
    }


    //2. Xem thông tin của 1 khóa học cụ thể (thông tin bao gồm thông tin khóa học và nhân viên tư vấn)
    @GetMapping("courses/{id}")
    public ResponseEntity<?> findCourseById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findCourseById(id));
    }
}
