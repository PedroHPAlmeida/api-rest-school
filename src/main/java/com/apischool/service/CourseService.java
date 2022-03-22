package com.apischool.service;

import com.apischool.entity.Course;
import com.apischool.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private ICourseRepository courseRepository;

    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    public Optional<Course> findCourseById(Long id){
        return courseRepository.findById(id);
    }

    public List<Course> findAllCourses(){
        return courseRepository.findAll();
    }

    public void deleteCourseById(Long id){
        courseRepository.deleteById(id);
    }
}
