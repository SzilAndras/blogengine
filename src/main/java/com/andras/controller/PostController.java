package com.andras.controller;

import com.andras.entity.Posts;
import com.andras.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/newPost")
    public List<Posts> addPost(@RequestBody Posts post){
        if(!this.postRepository.existsById(post.getPostId())){
            this.postRepository.save(post);
            return this.postRepository.findAll();
        } else {
            //TODO
            //exist error
            return null;
        }
    }

    @PostMapping(value = "/updatePost")
    public List<Posts> updatePost(@RequestBody Posts post){
        if(this.postRepository.existsById(post.getPostId())){
            this.postRepository.save(post);
            return this.postRepository.findAll();
        } else {
            //TODO
            //not exist error
            return null;
        }
    }

    @DeleteMapping(value = "/deletePost")
    public List<Posts> deletePost(@RequestBody Posts post){
        if(this.postRepository.existsById(post.getPostId())){
            this.postRepository.delete(post);
            return this.postRepository.findAll();
        } else {
            //TODO
            return this.postRepository.findAll();
        }
    }

}
