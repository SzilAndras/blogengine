package com.andras.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private Integer tagId;

    @Column(name = "tag", unique = true)
    private String tag;

    @ManyToMany
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    Set<Posts> taggedPosts;

    public Tags(String tag) {
        this.tag = tag;
    }

    public Tags(String tag, Set<Posts> taggedPosts) {
        this.tag = tag;
        /*this.taggedPosts = taggedPosts;*/
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
