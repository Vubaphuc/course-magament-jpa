package com.example.coursemanagementjpa.repository;

import com.example.coursemanagementjpa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select ct from Category ct where ct.id in (?1,?2,?3)")
    List<Category> findCategoriesById(Integer id1, Integer id2, Integer id3);
}