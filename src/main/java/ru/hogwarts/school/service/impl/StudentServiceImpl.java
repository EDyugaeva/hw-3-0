package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private Student findingStudent;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student changeStudent(Student student) {
        return studentRepository.save(student);
    }

    public Collection<Student> findStudentInAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findStudentInAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public String findStudentsFaculty(long id) {
        return findStudent(id).getFaculty();
    }


}
