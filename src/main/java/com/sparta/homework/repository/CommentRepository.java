package com.sparta.homework.repository;

import com.sparta.homework.entity.Comment;
import com.sparta.homework.repository.mapping.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
//    List<Comment>  findAllByHomeworkId(Long id);
    List<Comments> findAllByHomeworkId(Long id);
}
