package com.example.test1020.dto.Request;

import com.example.test1020.entity.Member;
import lombok.Data;


@Data
//@NoArgsConstructor 자동생성자가 있어서 안쓴다.
public class SignupReqDto {

    private String nickname;

    private String password;

    private String passwordCheck;


    //Dto를 entity로 변경해주는 메소드
    public Member toEntity(){

        return new  Member(this.nickname, this.password);
    }
}
