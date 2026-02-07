package com.example.xenon.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, max = 64, message = "Username should have between 4 and 64 characters")
    @Column(name = "username", unique = true, nullable = false, length = 64)
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email pattern")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

}
