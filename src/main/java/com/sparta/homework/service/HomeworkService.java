package com.sparta.homework.service;

import com.sparta.homework.dto.CommentResponseDto;
import com.sparta.homework.dto.HomeworkRequestDto;
import com.sparta.homework.dto.HomeworkResponseByIdDto;
import com.sparta.homework.dto.HomeworkResponseDto;
import com.sparta.homework.entity.Comment;
import com.sparta.homework.entity.Homework;
import com.sparta.homework.entity.User;
import com.sparta.homework.repository.HomeworkRepository;
import com.sparta.homework.repository.UserRepository;
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

    public HomeworkRequestDto createHomework(HomeworkRequestDto requestDto, String userNameToken) {
            User user = userRepository.findByUsername(userNameToken).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            homeworkRepository.saveAndFlush(new Homework(requestDto, user.getId(), user.getUsername()));
            return requestDto;
        }


    @Transactional(readOnly = true)
    public List<HomeworkResponseDto> getHomeworks() {
        List<Homework> homework = homeworkRepository.findAllByOrderByCreatedAtDesc();
        List<HomeworkResponseDto> homeworkResponse = new ArrayList<>();
        for (Homework response : homework){
            List<CommentResponseDto> comments = new ArrayList<>();
            List<Comment> commentList = response.getComments();
            for(Comment comment : commentList){
                comments.add(new CommentResponseDto(comment.getCreatedAt(),comment.getUsername(),comment.getComment()));
            }
            homeworkResponse.add(new HomeworkResponseDto(response.getContents(), response.getTitle(), response.getCreatedAt(), response.getUsername(),comments));
        }
        return homeworkResponse;
    }

    @Transactional(readOnly = true)
    public HomeworkResponseByIdDto getSelectHomeworks(Long id) {
        Homework homework = homeworkRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
        List<CommentResponseDto> comments = new ArrayList<>();
        List<Comment> commentList = homework.getComments();
        for(Comment comment : commentList){
            comments.add(new CommentResponseDto(comment.getCreatedAt(),comment.getUsername(),comment.getComment()));
        }
        return new HomeworkResponseByIdDto(homework.getContents(), homework.getTitle(), homework.getCreatedAt(), homework.getUsername(),comments);
    }

    @Transactional
    public HomeworkRequestDto update(Long id, HomeworkRequestDto requestDto, String userNameToken) {

            User user = userRepository.findByUsername(userNameToken).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

            Homework homework = homeworkRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("유효한 계정이 아니거나 게시글이 존재하지 않습니다.")
            );

            if(user.isADMIN()){
                homework.update(requestDto);
                return requestDto;
            }
            else if (homework.isWriter(user.getId())) {
                homework.update(requestDto);
                return requestDto;
            } else {
                throw new IllegalArgumentException("유효한 계정이 아닙니다");
            }
        }


    @Transactional
    public void delete(Long id, String userNameToken) {
            User user = userRepository.findByUsername(userNameToken).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

            Homework homework = homeworkRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("유효한 계정이 아니거나 게시글이 존재하지 않습니다.")
            );
            if(user.isADMIN()){
                homeworkRepository.deleteById(id);
            }
            else if (homework.isWriter(user.getId())) {
                homeworkRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("유효한 계정이 아닙니다");
            }
        }
}

