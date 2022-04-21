package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByColour(String colour);
    List<Faculty> findByColourIgnoreCase(String colour);
    List<Faculty> findByNameIgnoreCase(String name);
}
