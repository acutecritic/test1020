package com.example.test1020.dto.Response;

import com.example.test1020.entity.Comment;
import java.time.LocalDateTime;

public class CommentResDto {

    private Long id;

    private String comment;

    private String author;

    private LocalDateTime createdAt;


    //Post타입에서 DTO타입으로 변환
    public CommentResDto(Comment comment){

        this.id = comment.getId();

        this.comment = comment.getContent();

        this.author = comment.getMember().getNickname();

        this.createdAt = comment.getCreatedAt();

    }

}

