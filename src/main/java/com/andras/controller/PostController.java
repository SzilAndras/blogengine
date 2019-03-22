package com.andras.controller;

import com.andras.entity.Posts;
import com.andras.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/all")
    public List<Posts> getAllPost(){
        return this.postRepository.findAll();
    }
}
