package com.sparta.homework.controller;

import com.sparta.homework.dto.HomeworkRequestDto;
import com.sparta.homework.dto.HomeworkResponseByIdDto;
import com.sparta.homework.dto.HomeworkResponseDto;
import com.sparta.homework.entity.Homework;
import com.sparta.homework.service.HomeworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeworkController {
    private final HomeworkService homeworkService;

    @PostMapping("/api/post")
    public String createHomework(@RequestBody HomeworkRequestDto requestDto){
        return homeworkService.createHomework(requestDto);
    }
    @GetMapping("/api/posts")
    public List<HomeworkResponseDto> getHomeworks(){
        return  homeworkService.getHomeworks();
    }
    @GetMapping("/api/post/{id}")
    public HomeworkResponseByIdDto getSelectHomeworks(@PathVariable Long id){
       return homeworkService.getSelectHomeworks(id);
    }
    @PutMapping("/api/post/{id}")
    public String updateHomework(@PathVariable Long id, @RequestBody HomeworkRequestDto requestDto){
        return homeworkService.update(id,requestDto);
    }
    @DeleteMapping("/api/post/{id}")
    public String deleteHomework(@PathVariable Long id, @RequestBody HomeworkRequestDto requestDto){
        return homeworkService.delete(id,requestDto);
    }

}
