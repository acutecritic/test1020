package com.example.test1020.controller;


import com.example.test1020.dto.Response.*;
import com.example.test1020.dto.Request.PostReqDto;
import com.example.test1020.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController//Jason 형식으로 만들어줌
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public CommonResDto<?> create(@RequestBody PostReqDto dto){
        postService.create(dto);

        return new CommonResDto<>(true,null);
    }


    @GetMapping
    public CommonResDto<?> getAll(){

        List<PostResDto> resDtos = postService.readAll();

        return new CommonResDto<>(true,null);
    }

    @GetMapping("/{id}")
    public CommonResDto getOneById(@PathVariable("id") Long id){

        PostDetailResDto resDto = postService.readOneById(id);

        return new CommonResDto<>(true,null);
    }


    @PutMapping("/{id}")
    public CommonResDto update(@PathVariable("id") Long id, @RequestBody PostReqDto dto) {

        postService.update(id,dto);

       return new  CommonResDto<>(true,null);

    }


    @DeleteMapping("/{id}")
    public CommonResDto delete(@PathVariable("id") Long id) {

        postService.delete(id);

        return new  CommonResDto<>(true,null);

    }

}








