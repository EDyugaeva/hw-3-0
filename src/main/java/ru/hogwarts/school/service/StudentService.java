package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {
    Student addStudent(Student student);

    void deleteStudent(long id);

    Student changeStudent(Student student);

    Student findStudent(long id);

    Collection<Student> findStudentInAge(int age);

    Collection<Student> findStudentInAgeBetween(int min, int max);

    String findStudentsFaculty(long id);
}
