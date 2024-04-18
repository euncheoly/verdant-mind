package com.zeroinon.chatterboard.exception;

import com.zeroinon.chatterboard.base.constant.ResultCode;

public class UserAuthenticationException extends RuntimeException {


    public UserAuthenticationException(ResultCode resultCode) {
        super(resultCode.getMESSAGE());
    }
    public UserAuthenticationException(String message) {
        super(message);
    }


    public static class DuplicatedUserID extends UserAuthenticationException {
        public DuplicatedUserID(String message) {
            super(message);
        }
    }






}
