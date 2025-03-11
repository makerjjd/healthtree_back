package com.healthree.healthree_back.admin.user.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponseDto {
    private List<UserDto> users;
    private Integer totalPage;
    private Long total;
}
