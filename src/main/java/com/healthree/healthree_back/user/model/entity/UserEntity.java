package com.healthree.healthree_back.user.model.entity;

import java.time.LocalDate;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLRestriction;

import com.healthree.healthree_back.common.model.entity.BaseEntity;
import com.healthree.healthree_back.user.model.type.GenderType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@SQLRestriction("is_deleted=0")
public class UserEntity extends BaseEntity {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 100)
    @Comment("SNS 회원 ID")
    private String socialId;

    @Column(nullable = true, length = 20)
    @Comment("SNS 로그인 플랫폼")
    private String socialType;

    @Column(nullable = true, length = 50)
    private String email;

    @Column(nullable = true, length = 100)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false, length = 10)
    private GenderType gender;

    @Column(nullable = true, length = 50)
    private String refreshToken;

    @Column(nullable = false)
    private boolean isDeleted = false; // 추가된 필드
}
