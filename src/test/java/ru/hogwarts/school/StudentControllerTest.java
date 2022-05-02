//package ru.hogwarts.school;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.annotation.DirtiesContext;
//
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.util.UriComponentsBuilder;
//import ru.hogwarts.school.model.Faculty;
//import ru.hogwarts.school.model.Student;
//
//import java.net.URI;
//import java.util.Collection;
//import java.util.Set;
//
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class StudentControllerTest {
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    public void testPostStudent() {
//        Student student = new Student("Jane", 12);
//        ResponseEntity<Student> response = whenStudentHaveBeenCreated(getUriBuilder().build().toUri(), student);
//        thenStudentHaveBeenCreated(response);
//    }
//
//    @Test
//    public void testGetStudentById() {
//        Student student = new Student("StudentName", 12);
//        ResponseEntity<Student> createResponse = whenStudentHaveBeenCreated(getUriBuilder().build().toUri(), student);
//
//        thenStudentHaveBeenCreated(createResponse);
//        Student createdStudent = createResponse.getBody();
//
//        thenStudentHaveBeenFound(createdStudent.getId(), createdStudent);
//    }
//
//    @Test
//    public void testFindByAge() {
//        Student student18 = new Student("StudentName1", 18);
//        Student student20 = new Student("StudentName2", 20);
//        Student student15 = new Student("StudentName3", 15);
//        Student student14 = new Student("StudentName4", 14);
//
//        whenStudentHaveBeenCreated(getUriBuilder().build().toUri(), student18);
//        whenStudentHaveBeenCreated(getUriBuilder().build().toUri(), student20);
//        whenStudentHaveBeenCreated(getUriBuilder().build().toUri(), student15);
//        whenStudentHaveBeenCreated(getUriBuilder().build().toUri(), student14);
//
//        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
//        queryParams.add("age", "15");
//        thenStudentAreFoundInCriteria(queryParams, student15);
//    }
//
//    @Test
//    public void testFindByAgeBetween() {
//        Student student18 = new Student("StudentName1", 18);
//        Student student20 = new Student("StudentName2", 20);
//        Student student15 = new Student("StudentName3", 15);
//        Student student14 = new Student("StudentName4", 14);
//
//        whenStudentHaveBeenCreated(getUriBuilder().build().toUri(), student18);
//        whenStudentHaveBeenCreated(getUriBuilder().build().toUri(), student20);
//        whenStudentHaveBeenCreated(getUriBuilder().build().toUri(), student15);
//        whenStudentHaveBeenCreated(getUriBuilder().build().toUri(), student14);
//
//        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
//        queryParams.add("min", "15");
//        queryParams.add("max", "19");
//        thenStudentAreFoundInCriteria(queryParams, student15, student18);
//    }
//
//    @Test
//    public void testChangeStudent() {
//        Student student = new Student("StudentName", 12);
//        String newName = "NewStudentName";
//        int newAge = 20;
//
//        ResponseEntity<Student> responseEntity = whenStudentHaveBeenCreated(getUriBuilder().build().toUri(), student);
//        thenStudentHaveBeenCreated(responseEntity);
//        Student createdStudent = responseEntity.getBody();
//
//        whenChangingStudent(createdStudent, newAge, newName);
//        thenChangingStudent(createdStudent, newAge, newName);
//    }
//
//    @Test
//    public void testDeleteStudent() {
//        Student student = new Student("StudentName", 12);
//        ResponseEntity<Student> responseEntity = whenStudentHaveBeenCreated(getUriBuilder().build().toUri(), student);
//        thenStudentHaveBeenCreated(responseEntity);
//        Student deletingStudent = responseEntity.getBody();
//
//        URI getURI = getUriBuilder().path("/{id}").buildAndExpand(deletingStudent.getId()).toUri();
//        restTemplate.delete(getURI);
//        ResponseEntity<Void> response = restTemplate.getForEntity(getURI, Void.class);
//        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    private void thenChangingStudent(Student createdStudent, int newAge, String NewName) {
//        URI getURI = getUriBuilder().path("/{id}").buildAndExpand(createdStudent.getId()).toUri();
//        ResponseEntity<Student> updatedStudentRs = restTemplate.getForEntity(getURI, Student.class);
//
//        Assertions.assertThat(updatedStudentRs.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Assertions.assertThat(updatedStudentRs.getBody()).isNotNull();
//        Assertions.assertThat(updatedStudentRs.getBody().getAge()).isEqualTo(newAge);
//        Assertions.assertThat(updatedStudentRs.getBody().getName()).isEqualTo(NewName);
//    }
//
//    private void whenChangingStudent(Student student, int age, String name) {
//        student.setName(name);
//        student.setAge(age);
//        restTemplate.put(getUriBuilder().build().toUri(), student);
//    }
//
//    public void thenStudentAreFoundInCriteria(MultiValueMap<String, String> queryParams, Student... students) {
//        URI uri = getUriBuilder().queryParams(queryParams).build().toUri();
//        System.out.println(uri);
//
//        ResponseEntity<Set<Student>> response = restTemplate.exchange(
//                uri,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<Set<Student>>() {
//                });
//        Assertions.assertThat(response.getBody()).isNotNull();
//        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        Set<Student> actualResult = response.getBody();
//        resetIds(actualResult);
//        Assertions.assertThat(actualResult).containsExactlyInAnyOrder(students);
//    }
//
//    private void thenStudentHaveBeenFound(Long id, Student student) {
//        URI uri = getUriBuilder().path("/{id}").buildAndExpand(id).toUri();
//        ResponseEntity<Student> response = restTemplate.getForEntity(uri, Student.class);
//
//        Assertions.assertThat(response.getBody()).isEqualTo(student);
//        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
//
//    public void thenStudentHaveBeenCreated(ResponseEntity<Student> response) {
//        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        Assertions.assertThat(response.getBody()).isNotNull();
//        Assertions.assertThat(response.getBody().getId()).isNotNull();
//    }
//
//    public ResponseEntity<Student> whenStudentHaveBeenCreated(URI uri, Student student) {
//        return restTemplate.postForEntity(uri, student, Student.class);
//    }
//
//    public UriComponentsBuilder getUriBuilder() {
//        return UriComponentsBuilder.newInstance()
//                .scheme("http")
//                .host("localhost")
//                .port(port)
//                .path("/hogwarts/student");
//    }
//
//    public UriComponentsBuilder getUriBuilderForFaculty() {
//        return UriComponentsBuilder.newInstance()
//                .scheme("http")
//                .host("localhost")
//                .port(port)
//                .path("/hogwarts/faculty");
//    }
//
//    private void resetIds(Collection<Student> students) {
//        students.forEach(it -> it.setId(null));
//    }
//
//
//}
