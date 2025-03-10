package com.healthree.healthree_back.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthree.healthree_back.user.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByNameAndPhoneNumber(String name, String phoneNumber);

    Optional<UserEntity> findBySocialTypeAndSocialId(String socialType, String socialId);

    @Query(value = "SELECT * FROM user", nativeQuery = true, countQuery = "SELECT count(*) FROM user")
    Page<UserEntity> findAllWithDelete(Pageable pageable);

    @Query(value = "SELECT * FROM user WHERE id = :userId", nativeQuery = true)
    Optional<UserEntity> findWithDeleteById(Long userId);
}