package com.sparta.homework.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    //User를 가지고 게시글을 조회한다거나 코멘트를 조회하는 등의 기능은 만들지 않았기때문에 Post나 Comment의 @ManyToOne은 해주지 않았음

    public User(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
//    public boolean isADMIN(){
//    return getRole().equals(ADMIN);
//    }
}
