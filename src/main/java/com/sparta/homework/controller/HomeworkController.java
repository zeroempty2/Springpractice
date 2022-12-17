package com.sparta.homework.controller;

import com.sparta.homework.dto.HomeworkRequestDto;
import com.sparta.homework.dto.HomeworkResponseByIdDto;
import com.sparta.homework.dto.HomeworkResponseDto;
import com.sparta.homework.responseMessageData.DefaultRes;
import com.sparta.homework.responseMessageData.ResponseMessage;
import com.sparta.homework.responseMessageData.StatusCode;
import com.sparta.homework.responseMessageData.StatusEnum;
import com.sparta.homework.service.HomeworkService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeworkController {
    private final HomeworkService homeworkService;
    private final DefaultRes defaultRes;

    @PostMapping("/post")
    public HomeworkRequestDto createHomework(@RequestBody HomeworkRequestDto requestDto, HttpServletRequest request){
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
    public HomeworkRequestDto updateHomework(@PathVariable Long id, @RequestBody HomeworkRequestDto requestDto, HttpServletRequest request){
        return homeworkService.update(id,requestDto,request);
    }
    @DeleteMapping("/post/{id}")
    public ResponseEntity<DefaultRes> deleteHomework(@PathVariable Long id, HttpServletRequest request){
        homeworkService.delete(id,request);
        defaultRes.setStatusCode(StatusCode.OK);
        defaultRes.setResponseMessage(ResponseMessage.DELETE_SUCCESS);
        return new ResponseEntity<>(defaultRes, HttpStatus.OK);
    }
}
