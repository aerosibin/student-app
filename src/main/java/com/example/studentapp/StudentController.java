package com.example.studentapp;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private List<Student> students = new ArrayList<>();

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    @GetMapping
    public List<Student> viewStudents() {
        return students;
    }
}