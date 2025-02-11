package com.healthree.healthree_back.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Schema(description = "API 응답 메시지")
public class ApiResponseMessage {
    private String status;
    private String message;
    private Object data;

    public ApiResponseMessage() {
    }

    @Builder
    public ApiResponseMessage(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static ApiResponseMessage error(String errorCode) {
        return new ApiResponseMessage("Error", "", errorCode);
    }

    public static ApiResponseMessage successWithData(String message, Object result) {
        return new ApiResponseMessage("Success", message, result);
    }

    public static ApiResponseMessage success() {
        return new ApiResponseMessage("Success", "", "");
    }
}
