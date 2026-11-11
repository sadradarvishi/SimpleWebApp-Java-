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

    @Column(name = "post_uid", nullable = false)
    private String postUid;

    @ManyToOne
    @JoinColumn(name = "post_uid", referencedColumnName = "post_uid",
                  insertable = false, updatable = false)
    private PostEntity post;

    @Column(name = "user_uid", nullable = false)
    private String userUid;

    @ManyToOne
    @JoinColumn(name = "user_uid", referencedColumnName = "user_uid",
                  insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

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

    public String getPostUid() {return postUid;}

    public String getUserUid() {return userUid;}

    public void setPostUid(String postUid) {this.postUid = postUid;}

    public void setUserUid(String userUid) {this.userUid = userUid;}

    public Date getUpdatedAt() {return updatedAt;}

    public Boolean getDeleted() {return isDeleted;}

    public void setUpdatedAt(Date updatedAt) {this.updatedAt = updatedAt;}

    public void setDeleted(Boolean deleted) {isDeleted = deleted;}
}
