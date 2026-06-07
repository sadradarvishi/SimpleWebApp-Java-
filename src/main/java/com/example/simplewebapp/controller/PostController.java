package com.example.simplewebapp.controller;

import com.example.simplewebapp.model.PostEntity;
import com.example.simplewebapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService post_service;

    public PostController(PostService post_service) {
        this.post_service = post_service;
    }

    @GetMapping("/{post_uid}/")
    public ResponseEntity<PostEntity> getPost(
            @PathVariable String post_uid
    ) {
        return ResponseEntity.ok(post_service.getPost(post_uid));
    }

    @GetMapping("/")
    public ResponseEntity<List<PostEntity>> searchPosts() {
        return ResponseEntity.ok(post_service.searchPosts());
    }

    @PostMapping("/")
    public ResponseEntity<PostEntity> createPost(
            @RequestHeader("user_uid") String user_uid,
            @RequestBody Map<String, Object> input
    ) {
        return ResponseEntity.ok(post_service.createPost(user_uid, input));
    }

    @PatchMapping("/{post_uid}/")
    public ResponseEntity<PostEntity> updatePost(
            @PathVariable String post_uid,
            @RequestBody Map<String, Object> input
    ) {
        return ResponseEntity.ok(post_service.updatePost(post_uid, input));
    }

    @DeleteMapping("/{post_uid}/")
    public ResponseEntity<Void> deletePost(
            @PathVariable String post_uid
    ) {
        post_service.deletePost(post_uid);

        return ResponseEntity.noContent().build();
    }
}