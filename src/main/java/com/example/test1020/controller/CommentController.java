package com.example.test1020.controller;


import com.example.test1020.dto.Request.CommentReqDto;
import com.example.test1020.dto.Response.CommonResDto;
import com.example.test1020.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts/{post_id}/comments")
public class CommentController {

    private final CommentService commentService;


    @PostMapping
    public CommonResDto<?> create(@PathVariable("post_id") Long postId,
                                  @RequestBody CommentReqDto dto){
        commentService.create(dto);

        return new CommonResDto<>(true,null);
    }


    @GetMapping
    public CommonResDto<?> getAllByPostId(@PathVariable("post_id") Long postId){

        return new CommonResDto<>(true,null);
    }


    @PutMapping("/{comment_id}")
    public CommonResDto<?> update(@PathVariable("post_id") Long postId,
                                  @PathVariable("comment_id") Long commentId,
                                  @RequestBody CommentReqDto dto){

        return new CommonResDto<>(true,null);
    }


    @DeleteMapping("/{comment_id}")
    public CommonResDto<?> delete(@PathVariable("post_id") Long postId,
                                  @PathVariable("comment_id") Long commentId) {


        return new CommonResDto<>(true,null);
    }

    // 게시글 삭제
//    @DeleteMapping("/auth/posts/{postId")
//    public ResponseDto<?> deletePost(@PathVariable("postId") Long id,
//    @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//        return PostService.deletePost(id, userDetailsImpl.getUser().getUserId);


}
