package com.usaywhat.spocktestcontainersexample.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "firstname")
    private String firstName;

    @NotBlank
    @Column(name = "lastname")
    private String lastName;

}
