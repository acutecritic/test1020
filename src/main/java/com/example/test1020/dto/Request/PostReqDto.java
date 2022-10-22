package com.example.test1020.dto.Request;

import com.example.test1020.entity.Member;
import com.example.test1020.entity.Post;
import lombok.Data;


@Data
public class PostReqDto {

    private String title;

    private String content;


    public Post toEntity(Member member){

        return new Post(member, this.title, this.content);
    }
}
