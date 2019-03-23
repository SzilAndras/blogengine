package com.andras.controller;

import com.andras.entity.Category;
import com.andras.entity.Post;
import com.andras.entity.Tag;
import com.andras.repository.CategoryRepository;
import com.andras.repository.PostRepository;
import com.andras.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(value = "/all")
    public List<Post> getAllPost(){
        List<Post> posts = this.postRepository.findAll();
        for (Post post : posts) {
            for (Category cat: post.getCategories()) {
                cat.setPosts(null);
            }
        }
        return posts;
    }

    @PostMapping(value = "/newPost")
    public ResponseEntity addPost(@RequestBody Post post){
        if(!this.postRepository.existsById(post.getPostId())){
            post.setAdditionDate(new Date());
            post.setLastModification(new Date());
            this.postRepository.save(post);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/updatePost")
    public ResponseEntity updatePost(@RequestBody Post post){
        if(this.postRepository.existsById(post.getPostId())){
            post.setLastModification(new Date());
            this.postRepository.save(post);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*@PostMapping(value = "/addTag={tag}ToPostById={postId}")
    public void addTagById(@PathVariable("postId") Integer postId, @PathVariable("tag") String tag){
    }*/

    @PostMapping(value = "/addCategoryById={catId}ToPostId={postId}")
    public ResponseEntity addCategoryById(@PathVariable("postId") Integer postId, @PathVariable("catId") Integer catId){
        if (this.postRepository.existsById(postId) && (this.categoryRepository.existsByCategoryId(catId))){
            Category cat = this.categoryRepository.findFirstByCategoryId(catId);
            Post postTemp = this.postRepository.findFirstByPostId(postId);
            postTemp.addCategory(cat);
            this.postRepository.save(postTemp);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/removeCategoryById={catId}fromPostId={postId}")
    public ResponseEntity removeCategoryById(@PathVariable("postId") Integer postId, @PathVariable("catId") Integer catId){
        if (this.postRepository.existsById(postId) && (this.categoryRepository.existsByCategoryId(catId))){
            Category cat = this.categoryRepository.findFirstByCategoryId(catId);
            Post post = this.postRepository.findFirstByPostId(postId);
            if(post.getCategories().contains(cat)){
                post.removeCategory(cat);
                this.postRepository.save(post);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/deletePostById={postId}")
    public ResponseEntity deletePost(@PathVariable("postId") Integer postId){
        if(this.postRepository.existsById(postId)){
            Post post = this.postRepository.findFirstByPostId(postId);
            this.postRepository.delete(post);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/tags")
    public List<Tag> getAllTag(){
        return this.tagRepository.findAll();
    }

    @GetMapping(value = "/getPostsByCategoriesNameContaining={name}")
    public List<Post> getPostsByCategoriesNameContaining(@PathVariable("name") String name){
        List<Category> cats = this.categoryRepository.findAllByNameContaining(name);
        List<Post> posts = new ArrayList<>();
        for (Category cat: cats) {
            for(Post p : cat.getPosts()){
                if(!posts.contains(p)){
                    p.setCategories(null);
                    posts.add(p);
                }
            }
        }
        return posts;
    }

}
