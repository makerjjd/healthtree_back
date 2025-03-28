package com.healthree.healthree_back.admin.order.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrdersResposneDto {
    private List<AdminOrderDto> orders;
    private Integer totalPage;
    private Long total;
}
