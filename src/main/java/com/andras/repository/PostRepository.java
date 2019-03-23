package com.andras.repository;

import com.andras.entity.Category;
import com.andras.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Integer>{

    Post findFirstByPostId(Integer postId);
    List<Post> findAllByCategoriesContaining(Category cat);

}
