package com.example.simplewebapp.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Entity
public class CommentEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "comment_uid")
    private String commentUid;

    @ManyToOne
    @JoinColumn(name = "post_uid", referencedColumnName = "post_uid")
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "user_uid", referencedColumnName = "user_uid")
    private UserEntity user;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    public void setCommentUid(String commentUid) {
        this.commentUid = commentUid;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getCommentUid() {
        return commentUid;
    }

    public PostEntity getPost() {
        return post;
    }

    public UserEntity getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
