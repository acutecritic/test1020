package com.example.test1020.controller;


import com.example.test1020.dto.Request.CommentReqDto;
import com.example.test1020.dto.Response.CommentResDto;
import com.example.test1020.dto.Response.CommonResDto;
import com.example.test1020.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts/{post_id}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommonResDto<?> create(@PathVariable("post_id") Long postId, @RequestBody CommentReqDto dto){
        commentService.create(postId,dto);
        return new CommonResDto<>(true,null);
    }
    //특정 게시물 댓글 조회
    @GetMapping
    public CommonResDto<?> getAllByPostId(@PathVariable("post_id") Long postId){
        List<CommentResDto> resDtos = commentService.readAll(postId);
        return new CommonResDto<>(true,resDtos);
    }

    @PutMapping("/{comment_id}")
    public CommonResDto<?> update(@PathVariable("post_id") Long postId,
                               @PathVariable("comment_id") Long commentId,
                               @RequestBody CommentReqDto dto) {

        commentService.update(postId, commentId, dto);
        return new CommonResDto<>(true, null);
    }

    @DeleteMapping("/{comment_id}")
    public CommonResDto<?> delete(@PathVariable("post_id") Long postId,
                               @PathVariable("comment_id") Long commentId){

        commentService.delete(postId, commentId);
        return new CommonResDto<>(true, null);
    }

}