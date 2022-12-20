package com.sparta.homework.dto;



import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class CommentResponseDto {
    @ApiModelProperty(example = "생성일")
    private LocalDate createdAt;
    @ApiModelProperty(example = "이름")
    private String username;
    @ApiModelProperty(example = "댓글")
    private String comment;

    public CommentResponseDto(LocalDate createdAt, String username, String comment) {
        this.createdAt = createdAt;
        this.username = username;
        this.comment = comment;
    }
}
