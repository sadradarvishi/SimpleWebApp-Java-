package com.example.simplewebapp.controller;

import com.example.simplewebapp.model.CommentEntity;
import com.example.simplewebapp.service.CommentService;
import com.example.simplewebapp.dto.comment.GetCommentInputDTO;
import com.example.simplewebapp.dto.comment.GetCommentOutputDTO;
import com.example.simplewebapp.dto.comment.SearchCommentsInputDTO;
import com.example.simplewebapp.dto.comment.SearchCommentsOutputDTO;
import com.example.simplewebapp.dto.comment.CreateCommentInputDTO;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    private final CommentService comment_service;

    public CommentController(CommentService comment_service) {
        this.comment_service = comment_service;
    }

    @GetMapping("/{comment_uid}/")
    public ResponseEntity<GetCommentOutputDTO> getComment(@Valid @ParameterObject GetCommentInputDTO inputDTO) {
        return ResponseEntity.ok(comment_service.getComment(inputDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<SearchCommentsOutputDTO>> searchComments(@ParameterObject SearchCommentsInputDTO inputDTO) {
        return ResponseEntity.ok(comment_service.searchComments(inputDTO));
    }

    @PostMapping("/")
    public ResponseEntity<Void> createComment(@ParameterObject CreateCommentInputDTO inputDTO) {
        comment_service.createComment(inputDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{comment_uid}/")
    public ResponseEntity<CommentEntity> updateComment(
            @PathVariable String comment_uid,
            @RequestBody Map<String, Object> input
    ) {
        return ResponseEntity.ok(comment_service.updateComment(comment_uid, input));
    }

    @DeleteMapping("/{comment_uid}/")
    public ResponseEntity<Void> deleteComment(@PathVariable String comment_uid) {
        comment_service.deleteComment(comment_uid);
        return ResponseEntity.noContent().build();
    }
}
