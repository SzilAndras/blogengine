package com.andras.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private Integer tagId;

    @Column(name = "tag", unique = true)
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Tag)){
            return false;
        } else if(this == o){
            return true;
        } else {
            return this.getTag().equals(((Tag) o).getTag());
        }
    }

    public Tag() {
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public Tag(String tag) {
        this.tag = tag;
    }

    public Tag(String tag, Set<Post> taggedPosts) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
