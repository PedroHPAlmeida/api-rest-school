package com.apischool.service;

import com.apischool.entity.Student;
import com.apischool.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private IStudentRepository studentRepository;

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public Optional<Student> findStudentById(Long id){
        return studentRepository.findById(id);
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }
}
