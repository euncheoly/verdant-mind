package com.zeroinon.chatterboard.base.dto;

import com.zeroinon.chatterboard.base.constant.ResultCode;
import lombok.Getter;

@Getter
public class ResponseDTO {

    private final Boolean IS_SUCCESS;
    private final Integer CODE;
    private final String MESSAGE;

    public ResponseDTO(Boolean IS_SUCCESS, Integer CODE, String MESSAGE) {
        this.IS_SUCCESS = IS_SUCCESS;
        this.CODE = CODE;
        this.MESSAGE = MESSAGE;
    }


}
