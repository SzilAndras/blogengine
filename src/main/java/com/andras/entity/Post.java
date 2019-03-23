package com.andras.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "title")
    @Size(max = 50)
    private String title;


    @Column(name = "content")
    @Size(max = 500)
    private String content;

    @Column(name = "addition_date")
    private Date additionDate;

    @Column(name = "last_modification")
    private Date lastModification;

    @ManyToMany( cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany( cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();


    @Override
    public boolean equals(Object o){
        if(!(o instanceof Post)){
            return false;
        } else if (this == o) {
            return true;
        } else {
            return this.getPostId() != null && this.getPostId().equals(((Post) o).getPostId());
        }
    }


    public Post() {
    }

    public Post(String title, String content, Date additionDate, Date lastModification, List<String> tags, Set<Tag> addedtags) {
        this.title = title;
        this.content = content;
        this.additionDate = additionDate;
        this.lastModification = lastModification;
    }

    public void addTag(Tag tag){
        tag.getPosts().add(this);
        this.tags.add(tag);
    }

    public void removeTag(Tag tag){
        tags.remove(tag);
        tag.getPosts().remove(this);

    }

    public void addCategory(Category cat){
        cat.getPosts().add(this);
        this.categories.add(cat);
    }

    public void removeCategory(Category cat){
        categories.remove(cat);
    }

    public Integer getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(Date additionDate) {
        this.additionDate = additionDate;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> cats) {
        this.categories = cats;
    }
}
