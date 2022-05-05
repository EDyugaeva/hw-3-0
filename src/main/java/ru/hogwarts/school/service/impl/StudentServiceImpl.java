package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    private final Object flag = new Object();

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        Student addingStudent = studentRepository.save(student);
        logger.debug("Student {} was saved", student);
        return addingStudent;
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
        logger.debug("Student {} was deleted", id);

    }

    @Override
    public Student findStudent(long id) {
        Student findingStudent = studentRepository.findById(id).get();
        logger.debug("Student with id {} was found", id);
        return findingStudent;
    }

    @Override
    public Student changeStudent(Student student) {
        Student changingStudent = studentRepository.save(student);
        logger.debug("Student {} was changed", changingStudent);
        return changingStudent;
    }

    @Override
    public Collection<Student> findStudentInAge(int age) {
        Collection<Student> students = studentRepository.findByAge(age);
        logger.debug("Students were found");
        return students;
    }

    @Override
    public Collection<Student> findStudentInAgeBetween(int min, int max) {
        Collection<Student> students = studentRepository.findByAgeBetween(min, max);
        logger.debug("Students were found");
        return students;
    }

    @Override
    public String findStudentsFaculty(long id) {
        String faculty = findStudent(id).getFaculty();
        logger.debug("Students faculty is {}", faculty);
        return faculty;
    }

    @Override
    public int findAmountOfStudent() {
        int amount = studentRepository.findAmountOfStudents();
        logger.info("Amount is " + amount);
        return amount;
    }

    @Override
    public float findAverageAge() {
        float averageAge = studentRepository.findAverageAge();
        logger.debug("average age is " + averageAge);
        return averageAge;
    }

    @Override
    public List<Student> findTheLastFive() {
        List<Student> students = studentRepository.findTheLastFive();
        if (students.isEmpty()) {
            logger.error("Students are empty");
            throw new NullPointerException("Студенты отсутствуют");
        }
        return students;
    }

    @Override
    public List<Student> findStudentWithNameStartedWithA() {
        List<Student> allStudents = studentRepository.findAll();
        if (allStudents.isEmpty()) {
            logger.error("Students list is empty");
            throw new NotFoundException("Студенты отсутствуют");
        }
        List<Student> students = allStudents.stream()
                .parallel()
                .filter(student ->
                        student.getName().charAt(0) == 'A')
                .sorted(Comparator.comparing(student -> student.getName()))
                .collect(Collectors.toList());
        if (students.isEmpty()) {
            logger.error("There are none students with name started with A");
            throw new NotFoundException("Студентов с именем на букву А нет");
        }
        logger.debug("List Students with name started with A is done");
        return students;
    }

    @Override
    public double getAverageAge() {
        List<Student> allStudents = studentRepository.findAll();
        if (allStudents.isEmpty()) {
            logger.error("Students list is empty");
            throw new NotFoundException("Студенты отсутствуют");
        }
        return allStudents.stream()
                .parallel()
                .mapToInt(student -> student.getAge())
                .average()
                .getAsDouble();
    }

    @Override
    public void getTwoThreads() {
        List<Student> allStudents = studentRepository.findAllOrderById();
        System.out.println(allStudents.get(0));
        System.out.println(allStudents.get(1));
        logger.info("Main thread");
        new Thread(() -> {
            System.out.println(allStudents.get(2));
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(allStudents.get(3));
            logger.info("First thread");
        }).start();
        new Thread(() -> {
            System.out.println(allStudents.get(4));
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(allStudents.get(5));
            logger.info("Second thread");
        }).start();
    }

    @Override
    public void getTwoSynchronisedThreads() {
        List<Student> allStudents = studentRepository.findAllOrderById();
        printStudent(allStudents, 0);
        printStudent(allStudents, 1);
        logger.info("Main thread");
        new Thread(() -> {
            printStudent(allStudents, 2);
            printStudent(allStudents, 3);
            logger.info("First thread");
        }).start();
        new Thread(() -> {
            printStudent(allStudents, 4);
            printStudent(allStudents, 5);
            logger.info("Second thread");
        }).start();

    }

    private void printStudent(List<Student> students, int id) {
        synchronized (flag) {
            System.out.println(students.get(id));
        }
    }


}
