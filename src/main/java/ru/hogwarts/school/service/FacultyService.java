package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.stream.Collectors;

public interface FacultyService {

    Faculty addFaculty(Faculty faculty);

    Faculty deleteFaculty(long id);

    Faculty findFaculty(long lastId);

    Faculty changeFaculty(Faculty faculty);

     Collection<Faculty> findFacultyInColour(String colour);
}
