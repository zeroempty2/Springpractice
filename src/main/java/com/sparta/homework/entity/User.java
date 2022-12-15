package com.sparta.homework.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 4, max = 10)
    @Column(nullable = false)
    private String username;
    @Size(min = 8, max = 15)
    @Column(nullable = false)
    private String password;

    @OneToMany
    List<Homework> homeworks = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
