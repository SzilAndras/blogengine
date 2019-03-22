package com.andras.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "addition_date")
    private Date additionDate;

    @Column(name = "last_modification")
    private Date lastModification;

    /*@Column(name = "tags")
    private List<String> tags;*/

    @ManyToMany(mappedBy = "taggedPosts")
    Set<Tags> addedTags;

    @ManyToMany(mappedBy = "categoriedPosts")
    Set<Categories> addedCategores;





    public Posts() {
    }

    public Posts(String title, String content, Date additionDate, Date lastModification, List<String> tags, Set<Tags> addedtags) {
        this.title = title;
        this.content = content;
        this.additionDate = additionDate;
        this.lastModification = lastModification;
/*
        this.tags = tags;
*/
       /* addedTags = addedtags;*/
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
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

    /*public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }*/
}
