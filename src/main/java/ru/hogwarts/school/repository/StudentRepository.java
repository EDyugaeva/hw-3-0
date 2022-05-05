package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);
    List<Student> findByAgeBetween(int min, int max);

    @Query(value = "SELECT COUNT (*) from student ", nativeQuery = true)
    int findAmountOfStudents();

    @Query(value = "SELECT AVG (age) from student ", nativeQuery = true)
    float findAverageAge();

    @Query(value = "SELECT * from student order by id desc LIMIT 5 ", nativeQuery = true)
    List<Student> findTheLastFive();

    @Query(value = "SELECT * FROM student order by id", nativeQuery = true)
    List<Student> findAllOrderById();

}
