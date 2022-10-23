package com.example.test1020.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends TimeStemped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(cascade =  CascadeType.PERSIST,fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "MEMBER_ID",nullable = false)
    private Member member;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;


    public Comment(Member member,Post post,String content) {//**CommentReqDto에서 오류가 뜨면 여기의 순서를 확인할 것!

        this.member = member;
        this.post = post;
        this.content = content;

    }
    public void update(String content){

        this.content = content;
    }

    public boolean checkOwnerByMemberId(Long memberId){

        return this.member.getId().equals(memberId);
    }
    public boolean checkPostByPostId(Long postId) {
        return post.getId().equals(postId);
    }
}