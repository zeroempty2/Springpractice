package com.sparta.homework.service;

import com.sparta.homework.dto.HomeworkRequestDto;
import com.sparta.homework.dto.HomeworkResponseByIdDto;
import com.sparta.homework.dto.HomeworkResponseDto;
import com.sparta.homework.entity.Homework;
import com.sparta.homework.repository.HomeworkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class HomeworkService {
    private final HomeworkRepository homeworkRepository;
    public Homework createHomework(HomeworkRequestDto requestDto){
        Homework homework = new Homework(requestDto);
        homeworkRepository.save(homework);
        return homework;
    }

    @Transactional(readOnly = true)
    public List<HomeworkResponseDto> getHomeworks(){
        List<Homework> homework = homeworkRepository.findAllByOrderByCreatedAtDesc();
        List<HomeworkResponseDto> homeworkResponse = new ArrayList<>();
        for(Homework response : homework)
            homeworkResponse.add(new HomeworkResponseDto(response.getId(), response.getUsername(), response.getContents(),response.getTitle(),response.getCreatedAt()));
        return homeworkResponse;
    }

    @Transactional(readOnly = true)
    public HomeworkResponseByIdDto getSelectHomeworks(Long id){
        Homework homework = homeworkRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        return new HomeworkResponseByIdDto(homework.getUsername(), homework.getContents(), homework.getTitle(), homework.getCreatedAt());
    }

    @Transactional
    public String update(Long id, HomeworkRequestDto requestDto) {
            Homework homework = homeworkRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
            );
        if(homework.getPassword().equals(requestDto.getPassword()))
            homework.update(requestDto);
        else
            return "비밀번호가 일치하지 않습니다";
        return "수정완료";
    }
    @Transactional
    public String delete(Long id, HomeworkRequestDto requestDto) {
        Homework homework = homeworkRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        if(homework.getPassword().equals(requestDto.getPassword()))
            homeworkRepository.deleteById(id);
        else
            return "비밀번호가 일치하지 않습니다";
        return "삭제완료";
    }
}