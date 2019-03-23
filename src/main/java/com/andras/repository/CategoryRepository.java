package com.andras.repository;

import com.andras.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByCategoryId(Integer id);

    Category findFirstByCategoryId(Integer id);

    List<Category> findAllByNameContaining(String name);
}
