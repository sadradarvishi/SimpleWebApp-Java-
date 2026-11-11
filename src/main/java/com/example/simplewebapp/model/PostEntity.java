package com.example.simplewebapp.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Entity
public class PostEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "post_uid")
    private String postUid;

    @Column(name = "user_uid", nullable = false)
    private String userUid;

    @ManyToOne
    @JoinColumn(name = "user_uid", referencedColumnName = "user_uid",
            insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public void setPostUid(String postUid) {
        this.postUid = postUid;
    }

    public String getPostUid() {
        return postUid;
    }

    public UserEntity getUser() {
        return user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getUserUid() {return userUid;}

    public boolean isDeleted() {return isDeleted;}

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeleted(boolean deleted) {isDeleted = deleted;}

    public void setUserUid(String userUid) {this.userUid = userUid;}
}
