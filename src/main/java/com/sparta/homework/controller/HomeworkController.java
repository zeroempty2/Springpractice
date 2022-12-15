package com.sparta.homework.controller;

import com.sparta.homework.dto.HomeworkRequestDto;
import com.sparta.homework.dto.HomeworkResponseByIdDto;
import com.sparta.homework.dto.HomeworkResponseDto;
import com.sparta.homework.entity.Homework;
import com.sparta.homework.service.HomeworkService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeworkController {
    private final HomeworkService homeworkService;

    @PostMapping("/post")
    public String createHomework(@RequestBody HomeworkRequestDto requestDto, HttpServletRequest request){
        return homeworkService.createHomework(requestDto,request);
    }
    @GetMapping("/posts")
    public List<HomeworkResponseDto> getHomeworks(){
        return  homeworkService.getHomeworks();
    }
    @GetMapping("/post/{id}")
    public HomeworkResponseByIdDto getSelectHomeworks(@PathVariable Long id){
       return homeworkService.getSelectHomeworks(id);
    }
    @PutMapping("/post/{id}")
    public String updateHomework(@PathVariable Long id, @RequestBody HomeworkRequestDto requestDto, HttpServletRequest request){
        return homeworkService.update(id,requestDto,request);
    }
    @DeleteMapping("/post/{id}")
    public String deleteHomework(@PathVariable Long id,HttpServletRequest request){
        return homeworkService.delete(id,request);
    }

}
