package com.example.studentapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentControllerTest {

    @Test
    void testAddAndViewStudent() {
        StudentController controller = new StudentController();
        Student newStudent = new Student("1", "Jane Doe");
        
        controller.addStudent(newStudent);
        
        assertEquals(1, controller.viewStudents().size());
        assertEquals("Jane Doe", controller.viewStudents().get(0).getName());
    }
}