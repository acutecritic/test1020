package com.example.test1020.controller;


import com.example.test1020.dto.Request.SigninReqDto;
import com.example.test1020.dto.Request.SignupReqDto;
import com.example.test1020.dto.Response.CommonResDto;
import com.example.test1020.dto.TokenDto;
import com.example.test1020.security.TokenProvider;
import com.example.test1020.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")                    //타입      변수명
    public CommonResDto<?> signup(@RequestBody SignupReqDto dto){

        memberService.signup(dto);

        return new CommonResDto<>(true,null);

    }

    @PostMapping("/login")
    public CommonResDto<?> login(@RequestBody SigninReqDto dto, HttpServletResponse response){

        TokenDto tokenDto = memberService.login(dto);


        //respone header에 토큰 값을 넣어준다.
        //header 구조
        //authorization- 토큰값
        response.addHeader(TokenProvider.AUTHORIZATION_HEADER, tokenDto.getAccessToken());


        return new CommonResDto<>(true,null);

    }



}
