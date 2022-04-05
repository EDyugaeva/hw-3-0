package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {

    Faculty addFaculty(Faculty faculty);

    void deleteFaculty(long id);

    Faculty findFaculty(long id);

    Faculty changeFaculty(Faculty faculty);

    Collection<Faculty> findFacultyInColour(String colour);
}
