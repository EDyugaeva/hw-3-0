package ru.hogwarts.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import ru.hogwarts.school.service.impl.FacultyServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FacultyController.class)
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyServiceImpl facultyServiceImpl;


    @InjectMocks
    private FacultyController facultyController;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void postFaculty() throws Exception {
        Long id = 1L;
        String name = "Faculty";
        String colour = "red";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("colour", colour);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void getFaculty() throws Exception {
        Long id = 1L;
        String name = "Faculty";
        String colour = "red";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("colour", colour);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{id}", id)
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void putFaculty() throws Exception {
        Long id = 1L;
        String name = "Faculty";
        String colour = "red";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("colour", colour);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void deleteFaculty() throws Exception {
        Long id = 1L;
        String name = "Faculty";
        String colour = "red";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("colour", colour);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/{id}", id)
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(facultyRepository, atLeastOnce()).deleteById(id);
    }

    @Test
    public void findFacultyByColour() throws Exception {
        Long id1 = 1L;
        String name1 = "Faculty1";
        String colour1 = "red";

        Long id2 = 2L;
        String name2 = "Faculty2";

        Faculty faculty1 = new Faculty();
        faculty1.setId(id1);
        faculty1.setName(name1);
        faculty1.setColour(colour1);

        Faculty faculty2 = new Faculty();
        faculty2.setId(id2);
        faculty2.setName(name2);
        faculty1.setColour(colour1);

        when(facultyRepository.findByColour(any(String.class))).thenReturn(List.of(faculty1, faculty2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .queryParam("colour", colour1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty1, faculty2))));
    }

    @Test
    public void findFacultyByColourOrNameIgnoreCase() throws Exception {
        Long id1 = 1L;
        String name1 = "Faculty1";
        String colour1 = "red";

        Long id2 = 2L;
        String name2 = "faculty1";
        String colour2 = "Red";

        Faculty faculty1 = new Faculty();
        faculty1.setId(id1);
        faculty1.setName(name1);
        faculty1.setColour(colour1);

        Faculty faculty2 = new Faculty();
        faculty2.setId(id2);
        faculty2.setName(name2);
        faculty2.setColour(colour2);

        when(facultyRepository.findByColourIgnoreCase(any(String.class))).thenReturn(List.of(faculty1, faculty2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .queryParam("nameOrColour", colour1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty1, faculty2))));

        when(facultyRepository.findByNameIgnoreCase(any(String.class))).thenReturn(List.of(faculty1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .queryParam("nameOrColour", name1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty1))));
    }

    @Test
    public void findStudentsInFaculty() throws Exception {
        Long id1 = 1L;
        String name1 = "Faculty1";
        String colour1 = "red";

        Long idStudent1 = 1L;
        String nameStudent1 = "Student1";
        int ageStudent1 = 19;

        Long idStudent2 = 2L;
        String nameStudent2 = "Student2";
        int ageStudent2 = 15;

        Faculty faculty1 = new Faculty();
        faculty1.setId(id1);
        faculty1.setName(name1);
        faculty1.setColour(colour1);

        Student student1 = new Student();
        student1.setId(idStudent1);
        student1.setName(nameStudent1);
        student1.setAge(ageStudent1);

        Student student2 = new Student();
        student2.setId(idStudent2);
        student2.setName(nameStudent2);
        student2.setAge(ageStudent2);

        Set<Student> students = Set.of(student1, student2);
        faculty1.setStudentsInFaculty(students);

        when(facultyRepository.getById(any(Long.class))).thenReturn(faculty1);
        when(facultyServiceImpl.findStudentsInFaculty(any(Long.class))).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .queryParam("idFacultyToGetStudent", String.valueOf(1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(students)));
    }

}
