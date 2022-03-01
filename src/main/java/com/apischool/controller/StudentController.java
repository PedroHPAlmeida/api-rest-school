package com.apischool.controller;

import com.apischool.entity.Student;
import com.apischool.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/student")
public class StudentController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student findStudentById(@PathVariable(name = "id") Long id){
        return studentService.findStudentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não econtrado."));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> findAllStudents(){
        return studentService.findAllStudents();
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudentById(@PathVariable(name = "id") Long id, @RequestBody Student student){
        studentService.findStudentById(id)
                .map(studentBase -> {
                    modelMapper.map(student, studentBase);
                    studentService.saveStudent(studentBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado."));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentById(@PathVariable(name = "id") Long id){
        studentService.findStudentById(id)
                .map(student -> {
                    studentService.deleteStudentById(id);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não econtrado."));
    }
}
