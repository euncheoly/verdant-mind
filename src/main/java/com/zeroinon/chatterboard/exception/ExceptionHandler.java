package com.zeroinon.chatterboard.exception;

import com.zeroinon.chatterboard.base.constant.ResultCode;
import com.zeroinon.chatterboard.base.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {



    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleUserAuthenticationException(UserAuthenticationException ax) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                                                            false,
                                                            ResultCode.UNAUTHORIZED.getCODE(),
                                                            ResultCode.UNAUTHORIZED.getMESSAGE());

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(409));
    }



    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleUndefinedException(Exception ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                false,
                ResultCode.INTERNAL_SERVER_ERROR.getCODE(),
                ResultCode.INTERNAL_SERVER_ERROR.getMESSAGE());

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(500));
    }




}
