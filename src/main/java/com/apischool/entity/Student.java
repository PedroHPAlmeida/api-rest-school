package com.apischool.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student {

    public Student(Long id, String name, String birthDate, char sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        try{
            this.birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
        } catch (ParseException e) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "sex")
    private char sex;
}
