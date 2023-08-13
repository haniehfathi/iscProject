package com.java.isc.models;

import com.java.isc.enums.Roles;

import com.java.isc.enums.Roles;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="students")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @GeneratedValue
    @Id
    private Long id;

    @Column
    @NotBlank(message = "نام کاربر نباید خالی باشد")
    private String name;

    @Column
    @NotBlank(message = "نام خانوادگی کاربر نباید خالی باشد")
    private String family;


    @Email(message = "لطفا مقدار درست را وارد نمایید")
    @NotBlank(message = "ایمیل کاربر نباید خالی باشد")
    @Column(unique = true)
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    @NotBlank(message = "نام کاربری کاربر نباید خالی باشد")
    private String username;

    @Column
    @NotBlank(message = "پسوورد کاربر نباید خالی باشد")
    private String nationalCode;

    @Column
    private short numberOfUnits;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "units",
            joinColumns = {@JoinColumn(name = "stId")}
            ,inverseJoinColumns = {@JoinColumn(name = "courseId")})
    private List<Course> courses;

    public Student(String name, String family, String email, String phoneNumber, String address, String username, String nationalCode) {
        this.name = name;
        this.family = family;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.username = username;
        this.nationalCode = nationalCode;
    }

    public Student(Long id, String name, String family, String email, String phoneNumber, String address, String username, String nationalCode, short numberOfUnits) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.username = username;
        this.nationalCode = nationalCode;
        this.numberOfUnits = numberOfUnits;
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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public short getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(short numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
