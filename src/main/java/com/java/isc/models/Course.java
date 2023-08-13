package com.java.isc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="courses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Course {

    @GeneratedValue
    @Id
    private Long id;

    @Column
    @NotBlank(message = "عنوان نباید خالی باشد")
    @NotNull
    private String title;

    @Column
    @NotBlank(message = "واحد درس نباید خالی باشد")
    @NotNull
    private short unit;

    @ManyToOne
    @JsonIgnore
    private Professor professor;


    @ManyToMany(mappedBy = "courses")
    private List<Student> studentId;

    public Course(String title, short unit, Professor professorId) {
        this.title = title;
        this.unit = unit;
        this.professor = professorId;
    }

    public Course(Long id, String title, short unit, Professor professor) {
        this.id = id;
        this.title = title;
        this.unit = unit;
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public short getUnit() {
        return unit;
    }

    public void setUnit(short unit) {
        this.unit = unit;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Student> getStudentId() {
        return studentId;
    }

    public void setStudentId(List<Student> studentId) {
        this.studentId = studentId;
    }
}
