package com.sparta.homework.controller;

import com.sparta.homework.dto.HomeworkRequestDto;
import com.sparta.homework.dto.HomeworkResponseDto;
import com.sparta.homework.entity.Homework;
import com.sparta.homework.repository.HomeworkRepository;
import com.sparta.homework.service.HomeworkService;
import com.sparta.homework.repository.mapping.HomeworkMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeworkController {
    private final HomeworkService homeworkService;

    @PostMapping("/api/save")
    public Homework createHomework(@RequestBody HomeworkRequestDto requestDto){
        return homeworkService.createHomework(requestDto);
    }
    @GetMapping("/api/show")
    public HomeworkResponseDto getHomeworks(){
        return  homeworkService.getHomeworks();
    }
    @GetMapping("/api/show/{id}")
    public List<HomeworkMapping> getSelectHomeworks(@PathVariable Long id){
        return homeworkService.getSelectHomeworks(id);
    }
    @PutMapping("/api/update/{id}")
    public String updateHomework(@PathVariable Long id, @RequestBody HomeworkRequestDto requestDto){
        return homeworkService.update(id,requestDto);
    }
    @DeleteMapping("/api/delete/{id}")
    public String deleteHomework(@PathVariable Long id, @RequestBody HomeworkRequestDto requestDto){
        return homeworkService.delete(id,requestDto);
    }

}
