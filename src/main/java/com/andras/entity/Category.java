package com.andras.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "name", unique = true)
    @Size(min = 3, max = 6)
    private String name;

    @ManyToMany(mappedBy = "categories", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<Post> posts = new HashSet<>();

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> newPosts) {
        this.posts = newPosts;
    }

    public void removePost(Post post){
        this.posts.remove(post);
    }

    public Category() {
    }

    public Category(@Size(min = 3, max = 6) String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Post)){
            return false;
        } else if (this == o) {
            return true;
        } else {
            return this.getCategoryId() != null && this.getCategoryId().equals(((Category) o).getCategoryId());
        }
    }
}
