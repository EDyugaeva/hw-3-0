package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.impl.StudentServiceImpl;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")

public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student adding = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(adding);
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
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
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
    public ResponseEntity findStudentInAge(@RequestParam(required = false) Integer age) {
        if (age != null) {
            Collection<Student> students = studentService.findStudentInAge(age);
            if (!students.isEmpty()) {
                return ResponseEntity.ok(students);
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(params = {"min", "max"})
    public ResponseEntity findStudentBetweenAge(@RequestParam(required = false) Integer min,
                                                @RequestParam(required = false) Integer max) {
        if (max != null && min != null) {
            Collection<Student> students = studentService.findStudentInAgeBetween(min, max);
            if (!students.isEmpty()) {
                return ResponseEntity.ok(students);
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(params = {"idStudent"})
    public ResponseEntity<String> findStudentsFaculty(@RequestParam(required = false) long idStudent) {
        String faculty = studentService.findStudentsFaculty(idStudent);
        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/amount")
    public ResponseEntity<Integer> findAmountOfStudent() {
        return ResponseEntity.ok(studentService.findAmountOfStudent());
    }

    @GetMapping(path = "/average-age")
    public ResponseEntity<Float> findAverageAgeOfStudent() {
        return ResponseEntity.ok(studentService.findAverageAge());
    }

    @GetMapping(path = "/last-five")
    public ResponseEntity<List<Student>> findTheLastFive() {
        return ResponseEntity.ok(studentService.findTheLastFive());
    }

    @GetMapping(path = "stream/name-starts-with-a")
    public ResponseEntity<List<Student>> findStudentWithNameStartedWithA() {
        return ResponseEntity.ok(studentService.findStudentWithNameStartedWithA());
    }

    @GetMapping(path = "stream/average-age")
    public ResponseEntity<Double> getAverageAge() {
        return ResponseEntity.ok(studentService.getAverageAge());
    }
}
