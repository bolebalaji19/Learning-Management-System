package com.example.demo.repository;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Course;
import com.example.demo.entity.Progress;

public interface ProgressRepository extends JpaRepository<Progress, Long> {

	Progress findByUserAndCourse(Users users, Course course);
}
