package com.sparta.homework.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.homework.entity.Comment;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(example = "수정일")
    private LocalDateTime modifiedAt;

    public static CommentResponseDto valueOf(Comment comments) {
        return new CommentResponseDto(comments.getCreatedAt(),comments.getModifiedAt(),comments.getUser().getUsername(),comments.getComment());
    }

    public CommentResponseDto(LocalDateTime createdAt, LocalDateTime modifiedAt, String username, String comment) {
        this.createdAt = createdAt;
        this.username = username;
        this.comment = comment;
        this.modifiedAt = modifiedAt;
    }
}
