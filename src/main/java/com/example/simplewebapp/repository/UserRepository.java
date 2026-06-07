package com.example.simplewebapp.repository;

import com.example.simplewebapp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query("""
    select u
    from UserEntity u
    where u.isDeleted = false
""")
    List<UserEntity> searchUsers();

    @Query("""
    select u
    from UserEntity u
    where u.email = :email
      and u.isDeleted = false
""")
    Optional<UserEntity> findActiveUserByEmail(@Param("email") String email);
}