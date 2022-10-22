package com.example.test1020.dto.Response;

import com.example.test1020.entity.Post;
import java.time.LocalDateTime;




public class PostDetailResDto {

    private Long id;

    private String title;

    private String author;

    private String content;

    private LocalDateTime createdAt;


    //Post를 Dto로
    public PostDetailResDto(Post post){

        this.id = post.getId();

        this.title = post.getTitle();

        this.author = post.getAuthor();

        this.content = post.getContent();

        this.createdAt = post.getCreatedAt();
    }
}

