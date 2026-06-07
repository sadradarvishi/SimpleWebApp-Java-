package com.example.simplewebapp.controller;

import com.example.simplewebapp.model.CommentEntity;
import com.example.simplewebapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<CommentEntity> getComment(@PathVariable String comment_uid) {
        return ResponseEntity.ok(comment_service.getComment(comment_uid));
    }

    @GetMapping("/")
    public ResponseEntity<List<CommentEntity>> searchComments() {
        return ResponseEntity.ok(comment_service.searchComments());
    }

    @PostMapping("/")
    public ResponseEntity<CommentEntity> createComment(
            @RequestHeader("user_uid") String user_uid,
            @RequestBody Map<String, Object> input
    ) {
        return ResponseEntity.ok(comment_service.createComment(user_uid, input));
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
