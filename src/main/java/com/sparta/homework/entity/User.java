package com.sparta.homework.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.sparta.homework.entity.UserRoleEnum.ADMIN;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public boolean isADMIN(){
    return getRole().equals(ADMIN);
    }
}
