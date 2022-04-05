package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.impl.FacultyServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("faculty")

public class FacultyController {

    private final FacultyServiceImpl facultyService;

    public FacultyController(FacultyServiceImpl facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity addFaculty(@RequestBody Faculty faculty) {
        Faculty addingFaculty = facultyService.addFaculty(faculty);
        return ResponseEntity.ok(addingFaculty);
    }

    @GetMapping("{id}")
    public ResponseEntity getFaculty(@PathVariable long id) {
        Faculty gettingFaculty = facultyService.findFaculty(id);
        if (gettingFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gettingFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity changeFaculty(@RequestBody Faculty faculty) {
        Faculty changingFaculty = facultyService.changeFaculty(faculty);
        if (changingFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(changingFaculty);
    }

    @GetMapping(params = "colour")
    public ResponseEntity findStudentInAge(@RequestParam String colour) {
        Collection<Faculty> faculties = facultyService.findFacultyInColour(colour);
        if (faculties.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculties);
    }


}
