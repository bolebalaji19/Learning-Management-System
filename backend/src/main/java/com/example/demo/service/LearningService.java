package com.example.demo.service;

import com.example.demo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.EnrollRequest;
import com.example.demo.entity.Course;
import com.example.demo.entity.Learning;
import com.example.demo.entity.Progress;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.LearningRepository;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.UserRepository;
import java.util.*;

@Service
public class LearningService {

    @Autowired
    private LearningRepository learningRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private ProgressRepository progressRepository;

    public List<Course> getLearningCourses(Long userId) {
        Optional<Users> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isPresent()) {
            Users users = optionalUser.get();
            List<Course> learningCourses = new ArrayList<>();

            for (Learning learning : users.getLearningCourses()) {
                Course course = learning.getCourse();
                learningCourses.add(course);
            }

            return learningCourses;
        }

        return null;
    }
    
    public List<Learning> getEnrollments() {
    	return learningRepository.findAll();
    }

    public String enrollCourse(EnrollRequest enrollRequest) {
        Users users = userRepository.findById(enrollRequest.getUserId()).orElse(null);
        Course course = courseRepository.findById(enrollRequest.getCourseId()).orElse(null);

        if (users != null && course != null) {
            Learning existingLearning = learningRepository.findByUserAndCourse(users, course);
            if (existingLearning != null) {
                return "Course already enrolled";
            }

            Progress progress = new Progress();
            progress.setUser(users);
            progress.setCourse(course);
            progressRepository.save(progress);

            Learning learning = new Learning();
            learning.setUser(users);
            learning.setCourse(course);
            learningRepository.save(learning);

            return "Enrolled successfully";
        }

        return "Failed to enroll";
    }


    public void unenrollCourse(Long id) {
        learningRepository.deleteById(id);
    }
}

