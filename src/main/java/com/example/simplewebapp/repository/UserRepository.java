package com.example.simplewebapp.repository;

import com.example.simplewebapp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.simplewebapp.dto.user.SearchUserOutputDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query("""
    select new com.example.simplewebapp.dto.user.SearchUserOutputDTO(
        u.userUid,
        u.fullName,
        u.age,
        u.joinedAt,
        u.isDeleted,
        u.email
    )
    from UserEntity u
    where u.isDeleted = false
      and (cast(:fullName as string) is null or lower(u.fullName) like lower(concat('%', cast(:fullName as string), '%')))
      and (cast(:email as string) is null or lower(u.email) = lower(cast(:email as string)))
      and u.joinedAt >= coalesce(:from, u.joinedAt)
      and u.joinedAt <= coalesce(:to,   u.joinedAt)
""")
    List<SearchUserOutputDTO> searchUsers(@Param("fullName") String fullName,
                                          @Param("email") String email,
                                          @Param("from") Date joinedDateFrom,
                                          @Param("to") Date joinedDateTo);

    @Query("""
    select u
    from UserEntity u
    where u.email = :email
      and u.isDeleted = false
""")
    Optional<UserEntity> findActiveUserByEmail(@Param("email") String email);

    @Query("""
    select u
    from UserEntity u
    where u.userUid = :userUid
    and u.isDeleted = false
""")
    Optional<UserEntity> getUserById(@Param("userUid") String userUid);
}