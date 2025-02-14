package com.healthree.healthree_back.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthree.healthree_back.user.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByNameAndPhoneNumber(String name, String phoneNumber);
}