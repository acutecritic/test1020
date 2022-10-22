package com.example.test1020.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends TimeStemped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)   //여기에 쓰인것은 디비에 저장됨 그래서 패스워드 체크없음.
    @Id
    private Long id;

    @Column(nullable = false,unique = true)//디비에 영향을 줌.
    private String nickname;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRole role;

    //생성자
    public Member(String nickname, String password){

        this.nickname = nickname;
        this.password = password;
        this.role = MemberRole.MEMBER;
    }


}
