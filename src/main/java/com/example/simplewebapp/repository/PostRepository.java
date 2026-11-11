package com.example.simplewebapp.repository;

import com.example.simplewebapp.model.PostEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.simplewebapp.dto.post.GetPostOutputDTO;
import com.example.simplewebapp.dto.post.SearchPostOutputDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {

    @Query("""
        select new com.example.simplewebapp.dto.post.SearchPostOutputDTO(
            p.postUid,
            p.userUid,
            p.createdAt,
            p.updatedAt,
            p.content,
            p.title,
            p.isDeleted
        )
        from PostEntity p
        where p.isDeleted = false
        and (cast(:userUid as string) is null or lower(p.userUid) = lower(cast(:userUid as string)))
        and p.createdAt >= coalesce(:from, p.createdAt)
        and p.createdAt <= coalesce(:to,   p.createdAt)
    """)
    List<SearchPostOutputDTO> searchPosts(@Param("userUid") String userUid,
                                          @Param("from") Date createdAtFrom,
                                          @Param("to")   Date createdAtTo
    );

    @Query("""
        select new com.example.simplewebapp.dto.post.GetPostOutputDTO(
            p.postUid,
            p.userUid,
            p.createdAt,
            p.updatedAt,
            p.content,
            p.title,
            p.isDeleted
        )
        from PostEntity p
        where p.postUid = :postUid and p.isDeleted = false
""")
        Optional<GetPostOutputDTO> getPostById(@Param("postUid") String postUid);

    @Query("""
        select p
        from PostEntity p
        where p.postUid = :postUid and p.isDeleted = false
""")
        Optional<PostEntity> getPostByIdForUpdate(@Param("postUid") String postUid);

    @Modifying
    @Transactional
    @Query("""
    update PostEntity p
    set p.isDeleted = true
    where p.postUid = :postUid and p.isDeleted = false
""")
    int softDeleteById(@Param("postUid") String postUid);
}
