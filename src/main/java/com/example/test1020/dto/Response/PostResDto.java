package com.example.test1020.dto.Response;

import com.example.test1020.entity.Post;

import java.time.LocalDateTime;

public class PostResDto {

    private Long id;

    private String title;

    private String author;

    private LocalDateTime createdAt;


    //Post타입에서 DTO타입으로 변환
    public PostResDto(Post post){

        this.id = post.getId();

        this.title = post.getTitle();

        this.author = post.getAuthor();

        this.createdAt = post.getCreatedAt();

    }

}
