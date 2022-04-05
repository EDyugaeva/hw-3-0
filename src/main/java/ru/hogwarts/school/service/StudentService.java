package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {
    Student addStudent(Student student);
    Student deleteStudent(long id);
    Student changeStudent(Student student);
    Student findStudent(long lastId);
    Collection<Student> findStudentInAge(int age);

}
