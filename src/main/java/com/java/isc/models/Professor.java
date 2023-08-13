package com.java.isc.models;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="professors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Professor {

    @GeneratedValue
    @Id
    private Long id;

    @Column
    @NotBlank(message = "نام کاربر نباید خالی باشد")
    @NotNull
    private String name;

    @Column
    @NotBlank(message = "نام خانوادگی کاربر نباید خالی باشد")
    @NotNull
    private String family;

    @Email(message = "لطفا مقدار درست را وارد نمایید")
    @NotBlank(message = "ایمیل کاربر نباید خالی باشد")
    @NotNull
    @Column(unique = true)
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String address;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "professor")
    private List<Course> courses;

    public Professor(String name, String family, String email, String phoneNumber, String address) {
        this.name = name;
        this.family = family;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Professor(Long id, String name, String family, String email, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
