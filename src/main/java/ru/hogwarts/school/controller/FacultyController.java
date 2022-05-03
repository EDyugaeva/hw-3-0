package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.impl.FacultyServiceImpl;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("faculty")

public class FacultyController {

    private final FacultyServiceImpl facultyService;

    public FacultyController(FacultyServiceImpl facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
        Faculty addingFaculty = facultyService.addFaculty(faculty);
        return ResponseEntity.ok(addingFaculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
        Faculty gettingFaculty = facultyService.findFaculty(id);
        if (gettingFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gettingFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Faculty> changeFaculty(@RequestBody Faculty faculty) {
        Faculty changingFaculty = facultyService.changeFaculty(faculty);
        if (changingFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(changingFaculty);
    }

    @GetMapping(params = "colour")
    public ResponseEntity<Collection<Faculty>> findFacultyInColour(@RequestParam(required = false) String colour) {
        Collection<Faculty> faculties = facultyService.findFacultyInColour(colour);
        if (faculties.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculties);
    }

    @GetMapping(params = "nameOrColour")
    public ResponseEntity<Collection<Faculty>> findFacultyInNameIgnoreCase(@RequestParam(required = false) String nameOrColour) {
        Collection<Faculty> faculties = facultyService.findFacultyByNameOrColourIgnoreCase(nameOrColour);
        if (faculties.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculties);
    }

    @GetMapping(params = "idFacultyToGetStudent")
    public ResponseEntity<Collection<Student>> findStudentsInFaculty(@RequestParam(required = false) long idFacultyToGetStudent) {
        Collection<Student> students = facultyService.findStudentsInFaculty(idFacultyToGetStudent);
        if (!students.isEmpty()) {
            return ResponseEntity.ok(students);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/stream/the-longest-name")
    public ResponseEntity<String> findTheLongestFacultyName() {
        return ResponseEntity.ok(facultyService.findTheLongestFacultyName());
    }
}
