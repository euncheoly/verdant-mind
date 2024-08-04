package com.zeroinon.chatterboard.dto.response;

import lombok.Getter;


@Getter
public class ResponseDTO{

    private  Boolean RESULT;
    private  Integer CODE;
    private  String MESSAGE;

    public ResponseDTO(Boolean RESULT, Integer CODE, String MESSAGE) {
        this.RESULT = RESULT;
        this.CODE = CODE;
        this.MESSAGE = MESSAGE;
    }

}
