package com.sparta.homework.service;

import com.sparta.homework.dto.HomeworkRequestDto;
import com.sparta.homework.dto.HomeworkResponseByIdDto;
import com.sparta.homework.dto.HomeworkResponseDto;
import com.sparta.homework.entity.Homework;
import com.sparta.homework.entity.User;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.repository.HomeworkRepository;
import com.sparta.homework.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public String createHomework(HomeworkRequestDto requestDto, HttpServletRequest request) {
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

            homeworkRepository.saveAndFlush(new Homework(requestDto, user.getId(),user));
            return "게시글 작성 완료";
        }
        return "로그인 해 주십시오";
    }

    @Transactional(readOnly = true)
    public List<HomeworkResponseDto> getHomeworks() {
        List<Homework> homework = homeworkRepository.findAllByOrderByCreatedAtDesc();
        List<HomeworkResponseDto> homeworkResponse = new ArrayList<>();
        for (Homework response : homework)
            homeworkResponse.add(new HomeworkResponseDto(response.getId(), response.getContents(), response.getTitle(), response.getCreatedAt()));
        return homeworkResponse;
    }

    @Transactional(readOnly = true)
    public HomeworkResponseByIdDto getSelectHomeworks(Long id) {
        Homework homework = homeworkRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        return new HomeworkResponseByIdDto(homework.getContents(), homework.getTitle(), homework.getCreatedAt());
    }

    @Transactional
    public String update(Long id, HomeworkRequestDto requestDto, HttpServletRequest request) {
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

            Homework homework = homeworkRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 게시글은 존재하지 않습니다.")
            );
        if(homework.getUser().equals(user)){
                homework.update(requestDto);
                return "수정완료";
            }
        else{
            throw new IllegalArgumentException("유효한 계정이 아닙니다");
        }
        }
        return "로그인 해 주십시오";
    }

    @Transactional
    public String delete(Long id, HttpServletRequest request) {
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

            Homework homework = homeworkRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 게시글은 존재하지 않습니다.")
            );
            if(homework.getUser().equals(user)){
                homeworkRepository.deleteById(id);
                return "삭제완료";
            }
            else{
                throw new IllegalArgumentException("유효한 계정이 아닙니다");
            }
        }
        return "로그인 해 주십시오";
    }
}