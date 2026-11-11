package com.example.simplewebapp.service;

import com.example.simplewebapp.model.CommentEntity;
import com.example.simplewebapp.model.UserEntity;
import com.example.simplewebapp.repository.CommentRepository;
import com.example.simplewebapp.repository.PostRepository;
import com.example.simplewebapp.repository.UserRepository;
import com.example.simplewebapp.dto.comment.GetCommentInputDTO;
import com.example.simplewebapp.dto.comment.GetCommentOutputDTO;
import com.example.simplewebapp.dto.comment.SearchCommentsInputDTO;
import com.example.simplewebapp.dto.comment.SearchCommentsOutputDTO;
import com.example.simplewebapp.dto.comment.CreateCommentInputDTO;

import com.example.simplewebapp.dto.post.GetPostOutputDTO;

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

    public GetCommentOutputDTO getComment(GetCommentInputDTO inputDTO) {
        return comment_repository.getCommentById(inputDTO.commentUid())
                .orElseThrow(() -> new IllegalArgumentException("comment not found"));
    }

    public List<SearchCommentsOutputDTO> searchComments(SearchCommentsInputDTO inputDTO) {
        return comment_repository.searchComments(
                inputDTO.postUid(),
                inputDTO.userUid(),
                inputDTO.createdAtFrom(),
                inputDTO.createdAtTo(),
                inputDTO.isDeleted()
        );
    }

    public void createComment(CreateCommentInputDTO inputDTO) {
        UserEntity user = user_repository.getUserById(inputDTO.userUid())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        GetPostOutputDTO post = post_repository.getPostById(inputDTO.postUid())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        CommentEntity comment = new CommentEntity();
        comment.setUserUid(inputDTO.userUid());
        comment.setPostUid(inputDTO.postUid());
        comment.setContent(inputDTO.content());
        comment.setCreatedAt(new Date());
        comment.setDeleted(false);

        comment_repository.save(comment);
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
