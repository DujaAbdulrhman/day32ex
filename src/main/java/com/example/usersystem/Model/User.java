package com.example.usersystem.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private Integer id;
    @NotEmpty (message = "name cant be empty") @Size(min = 4 ,message = "name's size must be more than 4 letters")
    private String name;
     @NotEmpty(message = "username can't be empty") @Size(min = 4,message = "username length must be more than 4 letters")
     @Column(unique=true)
     private String username;
    @NotEmpty(message = "password can't be empty")
    private String password;
    @Email @NotEmpty(message = "email cant be empty")
    @Column(unique=true)
    private String email;
    @NotEmpty(message = "you must have a role")
    @Pattern(regexp = "User|Admin" ,message = "the role must be ether a User or an Admin")
    private String role;
    @NotNull
    private Integer age;

}
