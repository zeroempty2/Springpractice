package com.sparta.homework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponseDto {
    @ApiModelProperty(example = "게시글 내용")
    private String contents;
    @ApiModelProperty(example = "제목")
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(example = "생성일")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(example = "수정일")
    private LocalDateTime modifiedAt;
    @ApiModelProperty(example = "이름")
    private String username;
    @ApiModelProperty(example = "댓글 리스트")
    List<CommentResponseDto> comments;

    public PostResponseDto(String contents, String title, LocalDateTime createdAt, LocalDateTime modifiedAt, String username, List<CommentResponseDto> comments) {
        this.contents = contents;
        this.title = title;
        this.createdAt = createdAt;
        this.username = username;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}

