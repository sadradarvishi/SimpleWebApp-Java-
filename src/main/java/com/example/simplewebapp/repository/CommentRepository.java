package com.example.simplewebapp.repository;

import com.example.simplewebapp.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, String> {

    @Query("""
        select c
        from CommentEntity c
        join fetch c.post
        join fetch c.user
    """)
    List<CommentEntity> searchCommentsWithPostAndUser();
}
