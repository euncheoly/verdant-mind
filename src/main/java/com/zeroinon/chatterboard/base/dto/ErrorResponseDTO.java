package com.zeroinon.chatterboard.base.dto;

public class ErrorResponseDTO extends ResponseDTO {


    public ErrorResponseDTO(Boolean RESULT, Integer CODE, String MESSAGE) {
        super(RESULT, CODE, MESSAGE);
    }
}
