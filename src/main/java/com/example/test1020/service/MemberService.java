package com.example.test1020.service;


import com.example.test1020.dto.Request.SigninReqDto;
import com.example.test1020.dto.Request.SignupReqDto;
import com.example.test1020.dto.TokenDto;
import com.example.test1020.entity.Member;
import com.example.test1020.repository.MemberRepository;
import com.example.test1020.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    //닉네임 중복되어있는지, 비밀번호와 비밀번호확인이 동일한지
    //-> 회원가입가능
    public void signup(SignupReqDto dto){

        validateNicknameDuplicated(dto.getNickname());
        validatePasswordEquals(dto.getPassword(),dto.getPasswordCheck());

        Member member = dto.toEntity();
        memberRepository.save(member);
    }

    //로그인 - 아이디, 비밀번호 받아서 검증후 토큰생성 후 반환
    public TokenDto login(SigninReqDto dot){
        //인증이 되어있지 않은 Authentication 객체생성
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(dot.getNickname(),dot.getPassword());

        AuthenticationManager authenticationManager = authenticationManagerBuilder.getObject();
        Authentication authenticated = authenticationManager.authenticate(authentication);

        TokenDto tokenDto = tokenProvider.generateToken(authenticated);
        return tokenDto;
    }





    private void validateNicknameDuplicated(String nickname){
        boolean existByNickname = memberRepository.existsByNickname(nickname);
        if(existByNickname){
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

    }
    private void validatePasswordEquals(String password, String passwordCheck) {
            //==:주소까지 완전히 똑같은지, equals: 내용이 똑같은지.
        if (password.equals(passwordCheck)) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");

        }
    }

}





//        boolean existsByNickname = memberRepository.existsByNickname(dto.getNickname());
//
//        if(existsByNickname){
//            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
//        }else{
//            if()
//        }





