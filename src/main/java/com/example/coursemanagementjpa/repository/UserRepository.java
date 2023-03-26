package com.example.coursemanagementjpa.repository;

import com.example.coursemanagementjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUsersById(Integer id);

}