package com.example.simplewebapp.service;

import com.example.simplewebapp.model.PostEntity;
import com.example.simplewebapp.model.UserEntity;
import com.example.simplewebapp.repository.PostRepository;
import com.example.simplewebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public PostEntity getPost(String post_uid) {
        return postRepository.findById(post_uid).orElseThrow(() -> new RuntimeException("No user found"));
    }

    public List<PostEntity> searchPosts() {
        return postRepository.searchPostsWithUsers();
    }

    public PostEntity createPost(String user_uid, Map<String, Object> input) {

        if (!input.containsKey("title")) {
            throw new IllegalArgumentException("title is required");
        }

        if (!input.containsKey("content")) {
            throw new IllegalArgumentException("content is required");
        }

        UserEntity user = userRepository.findById(user_uid)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        PostEntity post = new PostEntity();

        post.setUser(user);
        post.setTitle((String) input.get("title"));
        post.setContent((String) input.get("content"));

        post.setCreatedAt(new Date());

        if (input.containsKey("updated_at")) {
            post.setUpdatedAt((Date) input.get("updated_at"));
        }

        return postRepository.save(post);
    }

    public PostEntity updatePost(String post_uid, Map<String, Object> input) {

        PostEntity post = postRepository.findById(post_uid)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        if (input.containsKey("title")) {
            post.setTitle((String) input.get("title"));
        }

        if (input.containsKey("content")) {
            post.setContent((String) input.get("content"));
        }

        post.setUpdatedAt(new Date());

        return postRepository.save(post);
    }

    public void deletePost(String post_uid) {

        PostEntity post = postRepository.findById(post_uid)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        postRepository.delete(post);
    }
}