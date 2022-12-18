package com.sparta.homework.entity;


import com.sparta.homework.dto.HomeworkRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    //{@OneToMany List<Comment>}가 단방향이 아니기 때문에 mappedBy추가, Comment가 삭제되면 Homework의 List<Comment>도 삭제될수 있도록 orphanRemoval = true로 바꿔줌,
    // Homework가 삭제되면 Homework의 Comment도 모두 삭제될 수 있도록 cascade = CascadeType.REMOVE해줌
    @OneToMany(mappedBy = "homework", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


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

    public void addComments(Comment comment) {
        this.comments.add(comment);
    }

}
