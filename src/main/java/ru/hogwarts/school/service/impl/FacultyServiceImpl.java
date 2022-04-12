package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty changeFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);

    }

    public Collection<Faculty> findFacultyInColour(String colour) {
        return facultyRepository.findByColour(colour);
    }

    public Collection<Faculty> findFacultyByNameOrColourIgnoreCase(String string) {
        Collection<Faculty> faculties = facultyRepository.findByNameIgnoreCase(string);
        if (faculties.isEmpty()) {
            faculties = facultyRepository.findByColourIgnoreCase(string);
        }
        return faculties;
    }


    public Collection<Student> findStudentsInFaculty(long id) {
        Faculty faculty = facultyRepository.getById(id);
        return faculty.getStudentsInFaculty();
    }


}
