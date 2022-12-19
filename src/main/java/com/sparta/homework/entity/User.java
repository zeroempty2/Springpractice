package com.sparta.homework.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.sparta.homework.entity.UserRoleEnum.ADMIN;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Pattern(regexp = "^[0-9a-z].{4,10}$",message = "최소 4자, 최대 10자 영어소문자와 숫자만 입력할 수 있습니다")
    @Column(nullable = false)
    private String username;
    @Pattern(regexp = "^[A-Za-z0-9#?!@$%^&*-].{8,15}$",message = "최소 8자, 최대15자 영어 대소문자, 특수문자를 입력할 수 있습니다.")
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
