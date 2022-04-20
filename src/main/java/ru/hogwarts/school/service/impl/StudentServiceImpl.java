package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

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

    public int findAmountOfStudent() {
        return studentRepository.findAmountOfStudents();
    }

    public float findAverageAge() {
        return studentRepository.findAverageAge();
    }


    @Override
    public List<Student> findTheLastFive() {
        List<Student> students = studentRepository.findTheLastFive();
        if (students.isEmpty()) {
            throw new NullPointerException("Студенты отсутствуют");
        }
        return students;
    }
}
