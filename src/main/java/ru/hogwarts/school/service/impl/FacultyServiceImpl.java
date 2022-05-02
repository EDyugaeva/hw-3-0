package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        Faculty addingFaculty = facultyRepository.save(faculty);
        logger.info("Faculty {} is saved", faculty);
        return addingFaculty;
    }

    @Override
    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
        logger.info("Faculty with id {} is deleted", id);
    }

    @Override
    public Faculty findFaculty(long id) {
        Faculty findingFaculty = facultyRepository.findById(id).get();
        logger.info("Faculty with id {} is found", id);
        return findingFaculty;
    }

    @Override
    public Faculty changeFaculty(Faculty faculty) {
        Faculty changingFaculty = facultyRepository.save(faculty);
        logger.info("Faculty {} is saved", faculty);
        return changingFaculty;
    }

    @Override
    public Collection<Faculty> findFacultyInColour(String colour) {
        Collection<Faculty> colouredFaculties = facultyRepository.findByColour(colour);
        logger.info("Search was made");
        return colouredFaculties;
    }

    @Override
    public Collection<Faculty> findFacultyByNameOrColourIgnoreCase(String string) {
        Collection<Faculty> faculties = facultyRepository.findByNameIgnoreCase(string);
        if (faculties.isEmpty()) {
            faculties = facultyRepository.findByColourIgnoreCase(string);
        }
        logger.debug("Search was made");
        return faculties;
    }

    @Override
    public Collection<Student> findStudentsInFaculty(long id) {
        Faculty faculty = facultyRepository.getById(id);
        Collection<Student> students = faculty.getStudentsInFaculty();
        logger.info("Students were found");
        return students;
    }


}
