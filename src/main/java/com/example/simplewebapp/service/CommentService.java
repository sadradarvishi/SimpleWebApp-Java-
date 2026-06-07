package com.example.simplewebapp.service;

import com.example.simplewebapp.model.CommentEntity;
import com.example.simplewebapp.model.PostEntity;
import com.example.simplewebapp.model.UserEntity;
import com.example.simplewebapp.repository.CommentRepository;
import com.example.simplewebapp.repository.PostRepository;
import com.example.simplewebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    private final CommentRepository comment_repository;
    private final PostRepository post_repository;
    private final UserRepository user_repository;

    public CommentService(
            CommentRepository comment_repository,
            PostRepository post_repository,
            UserRepository user_repository
    ) {
        this.comment_repository = comment_repository;
        this.post_repository = post_repository;
        this.user_repository = user_repository;
    }

    public CommentEntity getComment(String comment_uid) {
        return comment_repository.getById(comment_uid);
    }

    public List<CommentEntity> searchComments() {
        return comment_repository.searchCommentsWithPostAndUser();
    }

    public CommentEntity createComment(String user_uid, Map<String, Object> input) {
        if (!input.containsKey("content")) {
            throw new IllegalArgumentException("content is required");
        }

        if (!input.containsKey("post_uid")) {
            throw new IllegalArgumentException("post_uid is required");
        }

        UserEntity user = user_repository.findById(user_uid)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        PostEntity post = post_repository.findById((String) input.get("post_uid"))
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        CommentEntity comment = new CommentEntity();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent((String) input.get("content"));
        comment.setCreatedAt(new Date());

        return comment_repository.save(comment);
    }

    public CommentEntity updateComment(String comment_uid, Map<String, Object> input) {
        CommentEntity comment = comment_repository.findById(comment_uid)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        if (input.containsKey("content")) {
            comment.setContent((String) input.get("content"));
        }

        return comment_repository.save(comment);
    }

    public void deleteComment(String comment_uid) {
        CommentEntity comment = comment_repository.findById(comment_uid)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        comment_repository.delete(comment);
    }
}
