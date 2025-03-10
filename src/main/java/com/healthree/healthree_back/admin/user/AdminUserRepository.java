package com.healthree.healthree_back.admin.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthree.healthree_back.admin.user.model.entity.AdminUserEntity;

public interface AdminUserRepository extends JpaRepository<AdminUserEntity, Long> {
    Optional<AdminUserEntity> findByEmail(String email);

    @Query(value = "SELECT * FROM admin_user", nativeQuery = true, countQuery = "SELECT count(*) FROM admin_user")
    Page<AdminUserEntity> findWithDeleteAll(Pageable pageable);

    @Query(value = "SELECT * FROM admin_user WHERE id = :adminUserId", nativeQuery = true)
    Optional<AdminUserEntity> findWithDeleteById(Long adminUserId);

}
