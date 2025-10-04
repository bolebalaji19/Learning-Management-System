package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Assessment;
import com.example.demo.entity.Course;
import com.example.demo.entity.Users;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    List<Assessment> findByUserAndCourse(Users users, Course course);

	List<Assessment> findByUser(Users users);
}
