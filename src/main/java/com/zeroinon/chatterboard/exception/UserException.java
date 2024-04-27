package com.zeroinon.chatterboard.exception;

import com.zeroinon.chatterboard.base.constant.ResultCode;

public class UserException extends RuntimeException {


    public UserException(ResultCode resultCode) {
        super(resultCode.getMESSAGE());
    }
    public UserException(String message) {
        super(message);
    }


    public static class DuplicatedUserID extends UserException {
        public DuplicatedUserID(String message) {
            super(message);
        }
    }






}
