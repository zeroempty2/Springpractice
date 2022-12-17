package com.sparta.homework.service;

import com.sparta.homework.dto.HomeworkRequestDto;
import com.sparta.homework.dto.HomeworkResponseByIdDto;
import com.sparta.homework.dto.HomeworkResponseDto;
import com.sparta.homework.entity.Homework;
import com.sparta.homework.entity.User;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.repository.CommentRepository;
import com.sparta.homework.repository.HomeworkRepository;
import com.sparta.homework.repository.UserRepository;
import com.sparta.homework.repository.mapping.Comments;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.sparta.homework.entity.UserRoleEnum.ADMIN;


@Service
@RequiredArgsConstructor
public class HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final CommentRepository commentRepository;

    public HomeworkRequestDto createHomework(HomeworkRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            homeworkRepository.saveAndFlush(new Homework(requestDto, user.getId(), user.getUsername()));
            return requestDto;
        }
        throw new IllegalArgumentException("로그인 해 주십시오");
    }

    @Transactional(readOnly = true)
    public List<HomeworkResponseDto> getHomeworks() {
        List<Homework> homework = homeworkRepository.findAllByOrderByCreatedAtDesc();
        List<HomeworkResponseDto> homeworkResponse = new ArrayList<>();
        for (Homework response : homework)
            homeworkResponse.add(new HomeworkResponseDto(response.getContents(), response.getTitle(), response.getCreatedAt(), response.getUsername()));
        return homeworkResponse;
    }

    @Transactional(readOnly = true)
    public HomeworkResponseByIdDto getSelectHomeworks(Long id) {
        Homework homework = homeworkRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
        List<Comments> comments = commentRepository.findAllByHomeworkId(id);
        return new HomeworkResponseByIdDto(homework.getContents(), homework.getTitle(), homework.getCreatedAt(), homework.getUsername(),comments);
    }

    @Transactional
    public HomeworkRequestDto update(Long id, HomeworkRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다."));


            Homework homework = homeworkRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("유효한 계정이 아니거나 게시글이 존재하지 않습니다.")
            );

            if(user.getRole().equals(ADMIN)){
                homework.update(requestDto);
                return requestDto;
            }
            else if (homework.getUserId().equals(user.getId())) {
                homework.update(requestDto);
                return requestDto;
            } else {
                throw new IllegalArgumentException("유효한 계정이 아닙니다");
            }
        }
        throw new IllegalArgumentException("로그인 해 주십시오");
    }

    @Transactional
    public void delete(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

            Homework homework = homeworkRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("유효한 계정이 아니거나 게시글이 존재하지 않습니다.")
            );
            if(user.getRole().equals(ADMIN)){
                homeworkRepository.deleteById(id);
            }
            else if (homework.getUserId().equals(user.getId())) {
                homeworkRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("유효한 계정이 아닙니다");
            }
        }
    }
}
