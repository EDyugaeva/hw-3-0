package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@Entity(name = "faculty")
public class Faculty {
    public Faculty(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String colour;

    @OneToMany(mappedBy = "faculty")
    private Set<Student> studentsInFaculty;

    public void setStudentsInFaculty(Set<Student> studentsInFaculty) {
        this.studentsInFaculty = studentsInFaculty;
    }

    public Faculty() {

    }

    public Set<Student> getStudentsInFaculty() {
        return studentsInFaculty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(id, faculty.id) && Objects.equals(name, faculty.name) && Objects.equals(colour, faculty.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, colour);
    }
}
