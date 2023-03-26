package com.example.coursemanagementjpa.controller;


import com.example.coursemanagementjpa.dto.PageCourse;
import com.example.coursemanagementjpa.entity.request.UpsertCourseRequest;
import com.example.coursemanagementjpa.service.AdminService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    //1. Xem danh sách khóa học (có phân trang)
    @GetMapping("courses")
    public ResponseEntity<?> findAllCoursePage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(adminService.findAllCoursePage(page,pageSize));
    }

    //2. Tạo khóa học mới
    @PostMapping("courses")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createCourse(@RequestBody UpsertCourseRequest request) {
        return ResponseEntity.ok(adminService.createCourse(request));
    }

    //3. Lấy chi tiết khóa học
    @GetMapping("courses/{id}")
    public ResponseEntity<?> findCourseById(@PathVariable Integer id) {
        return ResponseEntity.ok(adminService.findCourseById(id));
    }


    //4. Cập nhật thông tin khóa học
    @PutMapping("courses/{id}")
    public ResponseEntity<?> updateCourseById(@PathVariable Integer id, @RequestBody UpsertCourseRequest request) {
        return ResponseEntity.ok(adminService.updateCourseById(id, request));
    }

    //5. Xóa khóa học
    @DeleteMapping("courses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourseById(@PathVariable Integer id) {
        adminService.deleteCourseById(id);
    }

}
