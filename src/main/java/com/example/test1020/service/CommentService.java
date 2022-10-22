package com.example.test1020.service;

import com.example.test1020.dto.Request.CommentReqDto;
import com.example.test1020.entity.Comment;
import com.example.test1020.entity.Member;
import com.example.test1020.entity.Post;
import com.example.test1020.repository.CommentRepository;
import com.example.test1020.repository.MemberRepository;
import com.example.test1020.repository.PostRepository;
import com.example.test1020.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional //댓글생성
    public void create(Long postId, CommentReqDto dto){


        Long memberId = SecurityUtil.getCurrentMemberId();

        Member member = memberRepository.findById(memberId).orElseThrow(//repository에서 멤버 아이디를 준비합니다..orElseThrow로 해당아이디가 아니면 던져라
                () -> new IllegalArgumentException("해당 아이디를 가진 멤버의 아이디가 없습니다.")
        );
        Post post = postRepository.findById(postId).orElseThrow(  //Null이면 던저라
                () -> new IllegalArgumentException("해당 아이디를 가진 게시글이 존재하지 않습니다.")
        );

        Comment comment = dto.toEntity(member,post);

        commentRepository.save(comment);
    }








}