package com.example.ProjectUsers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;


import javax.persistence.*;
import javax.swing.text.View;
import java.util.Objects;

@Entity
@Getter @Setter @ToString @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    //@JsonIgnore
    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String surname;

    @Column(nullable = false, length = 32)
    private String job;

    private Integer manager;

    public User(String name, String surname, String job, Integer manager) {
        this.name = name;
        this.surname = surname;
        this.job = job;
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return  Objects.equals(this.name, user.name)
                && Objects.equals(this.surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.name, this.surname);
    }

}