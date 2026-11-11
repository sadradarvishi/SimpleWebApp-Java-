package com.example.simplewebapp.controller;

import com.example.simplewebapp.service.PostService;
import com.example.simplewebapp.dto.post.GetPostInputDTO;
import com.example.simplewebapp.dto.post.GetPostOutputDTO;
import com.example.simplewebapp.dto.post.SearchPostInputDTO;
import com.example.simplewebapp.dto.post.SearchPostOutputDTO;
import com.example.simplewebapp.dto.post.CreatePostInputDTO;
import com.example.simplewebapp.dto.post.CreatePostOutputDTO;
import com.example.simplewebapp.dto.post.UpdatePostInputDTO;
import com.example.simplewebapp.dto.post.DeletePostInputDTO;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService post_service;

    public PostController(PostService post_service) {
        this.post_service = post_service;
    }

    @GetMapping("/{post_uid}/")
    public ResponseEntity<GetPostOutputDTO> getPost(@Valid @ParameterObject GetPostInputDTO inputDTO) {
        return ResponseEntity.ok(post_service.getPost(inputDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<SearchPostOutputDTO>> searchPosts(@ParameterObject SearchPostInputDTO inputDTO) {
        return ResponseEntity.ok(post_service.searchPosts(inputDTO));
    }

    @PostMapping("/")
    public ResponseEntity<CreatePostOutputDTO> createPost(@ParameterObject CreatePostInputDTO inputDTO) {
        return ResponseEntity.ok(post_service.createPost(inputDTO));
    }

    @PatchMapping("/{post_uid}/")
    public ResponseEntity<Void> updatePost(@Valid @ParameterObject UpdatePostInputDTO inputDTO) {
        post_service.updatePost(inputDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{post_uid}/")
    public ResponseEntity<Void> deletePost(@Valid @ParameterObject DeletePostInputDTO inputDTO) {
        post_service.deletePost(inputDTO);

        return ResponseEntity.noContent().build();
    }
}