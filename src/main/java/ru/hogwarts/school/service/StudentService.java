package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {

    Student addStudent(Student student);

    void deleteStudent(long id);

    Student changeStudent(Student student);

    Student findStudent(long id);

    Collection<Student> findStudentInAge(int age);

    Collection<Student> findStudentInAgeBetween(int min, int max);

    String findStudentsFaculty(long id);

    int findAmountOfStudent();

    float findAverageAge();

    List<Student> findTheLastFive();

    List<Student> findStudentWithNameStartedWithA();

    double getAverageAge();
}
