package com.zeroinon.chatterboard.exception;

import com.zeroinon.chatterboard.base.constant.ResultCode;

public class GeneralException extends RuntimeException {


    public GeneralException(Throwable cause) {
        super(cause);
    }

    public GeneralException(String message) {
        super(message);
    }




}
