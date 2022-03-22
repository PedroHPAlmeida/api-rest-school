package com.apischool.controller;

import com.apischool.entity.Course;
import com.apischool.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course saveCourseById(@RequestBody Course course){
        return courseService.saveCourse(course);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Course findStudentById(@PathVariable(name = "id") Long id){
        return courseService.findCourseById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado."));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Course> findAllCourses(){
        return courseService.findAllCourses();
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCourseById(@PathVariable(name = "id") Long id, @RequestBody Course course){
        courseService.findCourseById(id)
                .map(courseBase -> {
                    modelMapper.map(course, courseBase);
                    courseService.saveCourse(courseBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado."));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourseById(@PathVariable(name = "id") Long id){
        courseService.findCourseById(id)
                .map(course -> {
                    courseService.deleteCourseById(id);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado."));
    }
}
