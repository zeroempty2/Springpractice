package com.sparta.homework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import java.time.LocalDate;
import java.util.List;

@Getter
public class PostResponseDto {
    @ApiModelProperty(example = "게시글 내용")
    private String contents;
    @ApiModelProperty(example = "제목")
    private String title;
    @ApiModelProperty(example = "생성일")
    private LocalDate createdAt;
    @ApiModelProperty(example = "이름")
    private String username;
    @ApiModelProperty(example = "댓글 리스트")
    List<CommentResponseDto> comments;

    public PostResponseDto(String contents, String title, LocalDate createdAt, String username, List<CommentResponseDto> comments) {
        this.contents = contents;
        this.title = title;
        this.createdAt = createdAt;
        this.username = username;
        this.comments = comments;
    }
}

