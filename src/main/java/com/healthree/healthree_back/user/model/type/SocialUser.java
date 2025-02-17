package com.healthree.healthree_back.user.model.type;

import com.healthree.healthree_back.user.model.entity.UserEntity;

public interface SocialUser {
    String getSocialId();

    UserEntity toEntity();
}
