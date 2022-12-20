package com.sparta.homework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class PostRequestDto {
@ApiModelProperty(example = "게시글 내용")
private String contents;
@ApiModelProperty(example = "게시글 제목")
private String title;

}
