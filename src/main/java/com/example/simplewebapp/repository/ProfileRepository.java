package com.example.simplewebapp.repository;

import com.example.simplewebapp.model.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.simplewebapp.dto.profile.SearchProfileOutputDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {

    @Query("""
        SELECT p
        FROM ProfileEntity p
        where p.profileUid = :profileUid
    """)
    Optional<ProfileEntity> getProfileById(@Param("profileUid") String profileUid);


    @Query("""
        select new com.example.simplewebapp.dto.profile.SearchProfileOutputDTO(
           p.profileUid,
           p.userUid,
           u.fullName,
           u.age,
           u.joinedAt,
           u.isDeleted,
           u.email,
           p.displayName,
           p.bio,
           p.location,
           p.website,
           p.isDeleted
        )
        FROM ProfileEntity p
        JOIN p.user u
        WHERE p.isDeleted = false
    """)
    List<SearchProfileOutputDTO> searchProfileWithUser();

    @Query("""
        select p 
        from ProfileEntity p
        WHERE p.user.userUid = :userUid
        AND p.isDeleted = false
""")
    Optional<ProfileEntity> findProfileByUserId(@Param("userUid") String userUid);
}
