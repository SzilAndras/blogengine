package com.andras.controller;

import com.andras.entity.Category;
import com.andras.entity.Post;
import com.andras.repository.CategoryRepository;
import com.andras.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/all")
    @Transactional(readOnly = true)
    public List<Category> getAll(){
        List<Category> categories = this.categoryRepository.findAll();
        for (Category cat : categories) {
            for (Post post: cat.getPosts()) {
                post.setCategories(null);
            }
        }
        return categories;
    }

    @PostMapping(value = "/addCategory")
    public ResponseEntity addCategory(@RequestBody Category cat){
        if(!(this.categoryRepository.existsById(cat.getCategoryId()))){
            this.categoryRepository.save(cat);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/deleteById={id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Integer catId){
        if(this.categoryRepository.existsById(catId)){
            Category cat = this.categoryRepository.findFirstByCategoryId(catId);
            this.categoryRepository.delete(cat);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getPostsByCategoryId={catId}")
    public List<Post> getPostsByCategories(@PathVariable("catId") Integer catId){
        return postRepository.findAllByCategoriesContaining(categoryRepository.findFirstByCategoryId(catId));

    }

    /*@GetMapping(value = "getPostsByCategoryId={catId}Offset={offset}Limit={limit}")
    public List<Post> getPostsByCategoriesPageable(@PathVariable("catId") Integer catId,
                                                   @PathVariable("offset") Integer offset,
                                                   @PathVariable("limit") Integer limit){
        return postRepository.findAllByCategoriesContaining(categoryRepository.findFirstByCategoryId(catId));

    }*/

    @GetMapping(value = "/getCategoriesByNameContaining={name}")
    public List<Category> getCategoriesByNameContaining(@PathVariable("name") String name){
        return  this.categoryRepository.findAllByNameContaining(name);
    }

}
