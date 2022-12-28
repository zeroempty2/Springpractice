package com.sparta.homework.entity;

import com.sparta.homework.dto.PostRequestDto;
import com.sparta.homework.dto.SignupRequestDto;
import com.sparta.homework.exception.ExceptionController;
import com.sparta.homework.exception.exceptions.InvalidPasswordException;
import com.sparta.homework.exception.exceptions.IsNotAdminTokenException;
import com.sparta.homework.exception.exceptions.NotFoundPostException;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.repository.PostRepository;
import com.sparta.homework.repository.UserRepository;
import com.sparta.homework.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

import static com.sparta.homework.entity.UserRoleEnum.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserTest {
    @Test
    @DisplayName("일반 계정 생성 정상 케이스")
        //given
    void signUpUserNormalCase(){
        String username = "user";
        String password = "test123123";
        UserRoleEnum role = USER;

        //when
        User user = new User(username,password,role);
        //then
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @Nested
    @DisplayName("계정")
    class UserSet{
        private String username;
        private String password;
        private UserRoleEnum role;

        @BeforeEach
        void setup(){
            username ="user1";
            password = "test123123";
            role = USER;
        }
        @Test
        @DisplayName("로그인 실패 케이스(비밀번호 불일치)")
        void loginFail(){

            //given
            User user = new User(username,password,role);
            String wrongPassword = "tets123123";

            when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
            when(userRepository.findByUsername(username).get().getPassword().equals(wrongPassword)).thenThrow(new IllegalArgumentException("비밀번호가 일치하지 않습니다"));


            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.findByUsername(username).get().getPassword().equals(wrongPassword);
            });

            assertEquals(
                    "비밀번호가 일치하지 않습니다",
                     exception.getMessage()
            );

        }
    }
}