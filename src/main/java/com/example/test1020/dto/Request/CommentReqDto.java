package com.example.test1020.dto.Request;

import com.example.test1020.entity.Comment;
import com.example.test1020.entity.Member;
import com.example.test1020.entity.Post;
import lombok.Data;

@Data
public class CommentReqDto {

    private String content;

    public Comment toEntity(Member member, Post post){

        return new Comment(member, post, this.content);
    }
}
