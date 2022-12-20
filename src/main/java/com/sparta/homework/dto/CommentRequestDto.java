package com.sparta.homework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @ApiModelProperty(example = "댓글")
    private String comment;
}
