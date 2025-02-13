package com.healthree.healthree_back.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVAILD_USER_INFO(HttpStatus.BAD_REQUEST, "invalid user info"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error"),
    INVAILD_INVITE_CODE(HttpStatus.BAD_REQUEST, "invalid invite code"),
    ENCRYPT_ERROR(HttpStatus.BAD_REQUEST, "encrypt error"),
    INVAILD_USER_ID(HttpStatus.BAD_REQUEST, "invalid user id"),
    TOKEN_GENERATION_ERROR(HttpStatus.BAD_REQUEST, "token generation error"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "invalid token"),
    SSE_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "sse timeout"),
    SSE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "sse error"),
    INVALID_TEXT_MESSAGE(HttpStatus.BAD_REQUEST, "invalid text message"),
    AUTH_TOKEN_ERROR(HttpStatus.BAD_REQUEST, "wrong input"),
    INVALID_EMAIL(HttpStatus.REQUEST_TIMEOUT, "timout email verification"),
    EMAIL_SENDER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "email sender error"),
    WRONG_LOGIN_STEP(HttpStatus.FORBIDDEN, "wrong step"),
    ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "already registered"),
    BAD_AGREEMENT_REQUEST(HttpStatus.BAD_REQUEST, "bad agreement request"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "not found"),
    INVALID_ORDER_COUNT(HttpStatus.BAD_REQUEST, "invalid order count"),
    NOT_ENOUGH(HttpStatus.BAD_REQUEST, "not enough"),
    JSON_PARSE_ERROR(HttpStatus.BAD_REQUEST, "json parse error"),
    NOTI_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "notification error"),
    NOT_ENOUGH_INVITATION(HttpStatus.BAD_REQUEST, "not enough invitation"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "invalid parameter"),
    ALREADY_COMPLETE(HttpStatus.BAD_REQUEST, "already complate"),
    SMS_SEND_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "sms send error"),
    EXPIRED_AUTH_CODE(HttpStatus.BAD_REQUEST, "expired auth code"),
    ;

    private HttpStatus status;
    private String message;
}
