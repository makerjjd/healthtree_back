package com.healthree.healthree_back.common.handler;

import org.apache.commons.lang3.StringUtils;

import com.healthree.healthree_back.common.model.ErrorCode;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HealthTreeApplicationExceptionHandler extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    @Override
    public String getMessage() {
        if (StringUtils.isBlank(message)) {
            return errorCode.getMessage();
        }
        return String.format("%s, %s", errorCode.getMessage(), message);
    }

    public HealthTreeApplicationExceptionHandler(ErrorCode notFound, String message2) {
        errorCode = notFound;
        message = message2;
    }
}
