package com.zeroinon.chatterboard.dto.response;


import com.zeroinon.chatterboard.base.constant.ResultCode;
import lombok.Getter;

@Getter
public class GenericResponseDTO<T> extends ResponseDTO {

    private T data;


    public GenericResponseDTO(T data) {
        super(true, 10200, ResultCode.SUCCESS.getMESSAGE());
        this.data = data;
    }


    public static <T> GenericResponseDTO<T> of(T data) {
        return new GenericResponseDTO<>(data);
    }



}






