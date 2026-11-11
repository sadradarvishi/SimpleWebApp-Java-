package com.example.simplewebapp.service;

import com.example.simplewebapp.model.PostEntity;
import com.example.simplewebapp.model.UserEntity;
import com.example.simplewebapp.repository.PostRepository;
import com.example.simplewebapp.repository.UserRepository;
import com.example.simplewebapp.dto.post.GetPostInputDTO;
import com.example.simplewebapp.dto.post.GetPostOutputDTO;
import com.example.simplewebapp.dto.post.SearchPostInputDTO;
import com.example.simplewebapp.dto.post.SearchPostOutputDTO;
import com.example.simplewebapp.dto.post.CreatePostInputDTO;
import com.example.simplewebapp.dto.post.CreatePostOutputDTO;
import com.example.simplewebapp.dto.post.UpdatePostInputDTO;
import com.example.simplewebapp.dto.post.DeletePostInputDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public GetPostOutputDTO getPost(GetPostInputDTO inputDTO) {
        return postRepository.getPostById(inputDTO.postUid())
                .orElseThrow(() -> new RuntimeException("Post Not found"));
    }

    public List<SearchPostOutputDTO> searchPosts(SearchPostInputDTO inputDTO) {
        return postRepository.searchPosts(
                inputDTO.userUid(),
                inputDTO.createdAtFrom(),
                inputDTO.createdAtTo()
        );
    }

    public CreatePostOutputDTO createPost(CreatePostInputDTO inputDTO) {
        UserEntity user = userRepository.getUserById(inputDTO.userUid())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        PostEntity post = new PostEntity();

        post.setUserUid(inputDTO.userUid());
        post.setTitle(inputDTO.title());
        post.setContent(inputDTO.content());

        post.setCreatedAt(new Date());

        postRepository.save(post);

        return new CreatePostOutputDTO(
                post.getPostUid(),
                post.getUserUid(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getContent(),
                post.getTitle(),
                post.isDeleted()
        );
    }

    public void updatePost(UpdatePostInputDTO inputDTO) {
        PostEntity post = postRepository.getPostByIdForUpdate(inputDTO.postUid())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        if (inputDTO.content() != null) {
            post.setContent(inputDTO.content());
        }

        if (inputDTO.title() != null) {
            post.setContent(inputDTO.title());
        }

        post.setUpdatedAt(new Date());

        postRepository.save(post);
    }

    public void deletePost(DeletePostInputDTO inputDTO) {
        int updated = postRepository.softDeleteById(inputDTO.postUid());
        if (updated == 0) {
            throw new IllegalArgumentException("Post not found");
        }
    }
}