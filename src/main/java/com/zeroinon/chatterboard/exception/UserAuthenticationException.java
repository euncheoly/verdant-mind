package com.zeroinon.chatterboard.exception;

import com.zeroinon.chatterboard.base.constant.ResultCode;

public class UserAuthenticationException extends RuntimeException {


    private final ResultCode resultCode;

    public UserAuthenticationException(ResultCode resultCode) {
        super(resultCode.getMESSAGE());
        this.resultCode = resultCode;
    }





}
