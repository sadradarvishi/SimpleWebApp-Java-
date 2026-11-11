package com.example.simplewebapp.repository;

import com.example.simplewebapp.dto.comment.GetCommentOutputDTO;
import com.example.simplewebapp.dto.comment.SearchCommentsOutputDTO;
import com.example.simplewebapp.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, String> {

    @Query("""
        select new com.example.simplewebapp.dto.comment.SearchCommentsOutputDTO(
            c.commentUid,
            c.content,
            c.postUid,
            c.userUid,
            c.createdAt,
            c.updatedAt,
            c.isDeleted
        )
        from CommentEntity c
        where c.isDeleted = false
          and (cast(:postUid as string) is null or c.postUid = cast(:postUid as string))
          and (cast(:userUid as string) is null or c.userUid = cast(:userUid as string))
          and c.createdAt >= coalesce(:from, c.createdAt)
          and c.createdAt <= coalesce(:to,   c.createdAt)
    """)
    List<SearchCommentsOutputDTO> searchComments(@Param("postUid") String postUid,
                                                 @Param("userUid") String userUid,
                                                 @Param("from") Date createdAtFrom,
                                                 @Param("to") Date createdAtTo,
                                                 @Param("isDeleted") Boolean isDeleted);

    @Query("""
        select new com.example.simplewebapp.dto.comment.GetCommentOutputDTO(
            c.commentUid,
            c.content,
            c.postUid,
            c.userUid,
            c.createdAt,
            c.updatedAt,
            c.isDeleted
        )
        from CommentEntity c
        where c.commentUid = :commentUid and c.isDeleted = false
""")
    Optional<GetCommentOutputDTO> getCommentById(@Param("commentUid") String commentUid);
}
