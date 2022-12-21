package com.sparta.homework.entity;


import com.sparta.homework.dto.CommentResponseDto;
import com.sparta.homework.dto.PostRequestDto;
import com.sparta.homework.dto.PostResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;
    // mappedby "Post" 해줄시 Post_COMMENTS 테이블 생성되지 않음
    //{@OneToMany List<Comment>}가 단방향이 아니기 때문에 mappedBy추가, Comment가 삭제되면 Post의 코멘트 정보도 삭제될수 있도록 orphanRemoval = true로 바꿔줌,
    // Post가 삭제되면 Post의 Comment도 모두 삭제될 수 있도록 cascade = CascadeType.REMOVE해줌 /cascade는영속성 전이이다.
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    public Post(PostRequestDto requestDto,User user) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.user = user;
    }

        public void update(PostRequestDto requestDto) {
            this.contents = requestDto.getContents();
            this.title = requestDto.getTitle();
        }

    public boolean isWriter(User user){
    return getUser().getId().equals(user.getId());
    }

    public PostResponseDto getResponsePost(Post post, List<CommentResponseDto>comments){
        return new PostResponseDto(post.getContents(),post.getTitle(),post.getCreatedAt(), post.getModifiedAt(),post.getUser().getUsername(),comments);
    }

}
