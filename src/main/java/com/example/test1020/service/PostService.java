package com.example.test1020.service;

import com.example.test1020.dto.Request.PostReqDto;
import com.example.test1020.dto.Response.PostDetailResDto;
import com.example.test1020.dto.Response.PostResDto;
import com.example.test1020.entity.Member;
import com.example.test1020.entity.Post;
import com.example.test1020.repository.CommentRepository;
import com.example.test1020.repository.MemberRepository;
import com.example.test1020.repository.PostRepository;
import com.example.test1020.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;


    //게스글을 생성하기 위해서 무엇이 필요할까?->생성자를 보자 - title, content, member
    // Utill에서 가지고온다.(service에서 정보가 없을때 Util에서 가지고옴)
    public void create(PostReqDto dto){

        //현재로그인한 멤버의 아이디를가지고 온다.SecurityUtil
        Long memberId = SecurityUtil.getCurrentMemberId();

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디를 가진 멤버가 없습니다."));

        Post post = dto.toEntity(member);

        postRepository.save(post);
    }

    //게시글 전체조회 //게시글은 전부 가져와서 디티오로 ㅂㄴ환후 반환
    public List<PostResDto> readAll(){

        List<Post> postList = postRepository.findAll();

        List<PostResDto> postResDtoList = postList.stream()
                        .map(post-> new PostResDto(post))
                        .collect(Collectors.toList());

        return postResDtoList;
    }


    //단건조화

  public PostDetailResDto readOneById(Long postId){

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디를 가진 게시글이 존재하지 않습니다.")
        );

        return new PostDetailResDto(post);
  }

  //게시글 업데이트
    @Transactional
    public void update(Long id, PostReqDto dto){


        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디를 가진 게시글이 존재하지 않습니다.")
        );

        checkOwner(post,SecurityUtil.getCurrentMemberId());

        post.update(dto.getTitle(), dto.getContent());

        postRepository.save(post);
    }

    @Transactional
    public void delete(Long id){

        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디를 가진 게시글이 존재하지 않습니다.")
        );

        checkOwner(post,SecurityUtil.getCurrentMemberId());

        //댓글 삭제
        commentRepository.deleteAllByPostId(post.getId());

        //게시글 삭제
        postRepository.deleteById(id);
    }




    //현재 이 포스트의 주인이 해당 멤버아이드를 가진 멤버와 같은지 확인한다.
    private void checkOwner(Post post, Long memberId){

        if(!post.checkOwnerByMemberId(memberId)){
            throw new IllegalArgumentException("회원님이 작성한 글이 아닙니다.");
        }


    }

}

