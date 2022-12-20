package com.sparta.homework.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(example = "생성일")
    private LocalDateTime createdAt;
    @ApiModelProperty(example = "이름")
    private String username;
    @ApiModelProperty(example = "댓글")
    private String comment;

    public CommentResponseDto(LocalDateTime createdAt, String username, String comment) {
        this.createdAt = createdAt;
        this.username = username;
        this.comment = comment;
    }
}
