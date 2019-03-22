package com.andras.controller;

import com.andras.entity.Categories;
import com.andras.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(value = "/all")
    public List<Categories> getAll(){
        return this.categoryRepository.findAll();
    }

    @PostMapping(value = "/addCategory")
    public List<Categories> addCategory(@RequestBody Categories cat){
        this.categoryRepository.save(cat);
        return this.categoryRepository.findAll();
    }

    @DeleteMapping(value = "/delete")
    public List<Categories> deleteCategory(@RequestBody Categories cat){
        if(this.categoryRepository.existsById(cat.getCategoryId())){
            this.categoryRepository.delete(cat);
            return this.categoryRepository.findAll();
        } else {
            //TODO
            return  this.categoryRepository.findAll();
        }
    }
}
