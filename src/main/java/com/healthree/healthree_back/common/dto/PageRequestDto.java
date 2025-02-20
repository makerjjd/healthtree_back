package com.healthree.healthree_back.common.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import io.micrometer.common.util.StringUtils;
import lombok.Data;

@Data
public class PageRequestDto {
    private Integer limit = 6;
    private Integer page = 1;
    private String search;

    public Pageable getPageable() {
        return PageRequest.of(this.page - 1, this.limit);
    }

    public String nextUrl() {
        String baseUrl = "?";
        baseUrl += "page=" + (page + 1);

        if (limit != 5) {
            baseUrl += "&limit=" + limit;
        }

        if (StringUtils.isNotBlank(search)) {
            baseUrl += "&search=" + search;
        }

        return baseUrl;
    }
}
