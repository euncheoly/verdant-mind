package com.zeroinon.chatterboard.base.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;


@Getter
public enum ResultCode {


    // Login related results
    SUCCESS(10200, HttpStatus.OK, "Ok"),
    UNAUTHORIZED(10401, HttpStatus.UNAUTHORIZED, "Invalid credentials or user not found"),
    FORBIDDEN(10403, HttpStatus.FORBIDDEN, "User not authorized to perform this action"),

    // Member related results
    DATA_ALREADY_EXISTS(10409, HttpStatus.CONFLICT, "Data already exists"),

    // Comment related results
    COMMENT_NOT_FOUND(10404, HttpStatus.NOT_FOUND, "Comment not found"),
    COMMENT_UPDATE_FAILED(10400, HttpStatus.BAD_REQUEST, "Failed to update comment"),
    COMMENT_DELETED(10200, HttpStatus.OK, "Comment deleted successfully"),

    // Reply related results
    REPLY_NOT_FOUND(10404, HttpStatus.NOT_FOUND, "Reply not found"),
    REPLY_UPDATE_FAILED(10400, HttpStatus.BAD_REQUEST, "Failed to update reply"),
    REPLY_DELETED(10200, HttpStatus.OK, "Reply deleted successfully"),

    // General results (add more as needed)
    INTERNAL_SERVER_ERROR(10500, HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    BAD_REQUEST(10400, HttpStatus.BAD_REQUEST, "Invalid request");


    private final Integer CODE;
    private final HttpStatus HTTP_STATUS;
    private final String MESSAGE;

    ResultCode(Integer CODE, HttpStatus HTTP_STATUS, String MESSAGE) {
        this.CODE = CODE;
        this.HTTP_STATUS = HTTP_STATUS;
        this.MESSAGE = MESSAGE;
    }




}
