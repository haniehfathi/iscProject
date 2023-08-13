package com.java.isc.models;

import com.java.isc.enums.Roles;
import com.sun.istack.Nullable;
import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "__users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @GeneratedValue
    @Id
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private boolean active;

    @Column
    public String role;

    @OneToOne
    @Nullable
    private Student student;

    public User(String username, String password, boolean active, String role) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = role;
    }
    public User(String username, String password, boolean active, String role,Student student) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = role;
        this.student=student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
