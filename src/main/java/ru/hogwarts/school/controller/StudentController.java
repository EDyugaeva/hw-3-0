package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("student")

public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity addStudent(@RequestBody Student student) {
        Student adding = studentService.addStudent(student);
        return ResponseEntity.ok(adding);
    }

    @GetMapping("{id}")
    public ResponseEntity getStudent(@PathVariable long id) {
        Student findingStudent = studentService.findStudent(id);
        if (findingStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findingStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        Student deletingStudent = studentService.deleteStudent(id);
        if (deletingStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletingStudent);
    }

    @PutMapping
    public ResponseEntity changeStudent(@RequestBody Student student) {
        Student changingStudent = studentService.changeStudent(student);
        if (changingStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(changingStudent);
    }

    @GetMapping(params = "age")
    public ResponseEntity findStudentInAge(@RequestParam int age) {
        Collection<Student> students = studentService.findStudentInAge(age);
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }
}
