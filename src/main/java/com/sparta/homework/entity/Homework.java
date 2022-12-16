package com.sparta.homework.entity;


import com.sparta.homework.dto.HomeworkRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Homework extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long userId;


    public Homework(HomeworkRequestDto requestDto, Long userId, String username) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.userId = userId;
        this.username = username;
    }

    public void update(HomeworkRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }

}
