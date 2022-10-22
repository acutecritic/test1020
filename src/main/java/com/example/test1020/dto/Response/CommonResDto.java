package com.example.test1020.dto.Response;

import lombok.Getter;

@Getter

public class CommonResDto<T> {
    private Boolean success;

    private  T data;

    public CommonResDto(Boolean success, T data){
        this.success = success;
        this.data = data;
    }
}
