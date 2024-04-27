package com.zeroinon.chatterboard.dto.response;

public class ErrorResponseDTO extends ResponseDTO {


    public ErrorResponseDTO(Boolean RESULT, Integer CODE, String MESSAGE) {
        super(RESULT, CODE, MESSAGE);
    }
}
