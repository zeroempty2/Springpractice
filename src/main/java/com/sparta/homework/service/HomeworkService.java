package com.sparta.homework.service;

import com.sparta.homework.dto.HomeworkRequestDto;
import com.sparta.homework.entity.Homework;
import com.sparta.homework.repository.HomeworkRepository;
import com.sparta.homework.repository.mapping.HomeworkMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Homework> getHomeworks(){
        return homeworkRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public List<HomeworkMapping> getSelectHomeworks(Long id){
        return homeworkRepository.findAllById(id);
    }

    @Transactional
    public String update(Long id, HomeworkRequestDto requestDto) {
        if(String.valueOf(homeworkRepository.getPasswordById(id).getPassword()).equals(requestDto.getPassword())){
            Homework homework = homeworkRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
            );
            homework.update(requestDto);
        }
        else{
            return "비밀번호가 일치하지 않습니다";
        }
        return "수정완료";
    }
    @Transactional
    public String delete(Long id, HomeworkRequestDto requestDto) {
        if(String.valueOf(homeworkRepository.getPasswordById(id).getPassword()).equals(requestDto.getPassword())){
            homeworkRepository.deleteById(id);
        }
        else{
            return "비밀번호가 일치하지 않습니다";
        }
        return "삭제완료";
    }
}