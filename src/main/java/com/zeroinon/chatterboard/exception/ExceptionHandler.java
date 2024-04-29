package com.zeroinon.chatterboard.exception;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import com.zeroinon.chatterboard.base.constant.ResultCode;
import com.zeroinon.chatterboard.dto.response.ErrorResponseDTO;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {



    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleUserAuthenticationException(UserException.InvalidPassword iv) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                                                            false,
                                                            ResultCode.UNAUTHORIZED.getCODE(),
                                                            ResultCode.UNAUTHORIZED.getMESSAGE());

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(401));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleUnauthorizedTokenException(GeneralException.InvalidToken it) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                false,
                ResultCode.FORBIDDEN.getCODE(),
                ResultCode.FORBIDDEN.getMESSAGE());

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(403));
    }


    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleDuplicatedDataException(UserException.DuplicatedUserID du) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                false,
                ResultCode.DATA_ALREADY_EXISTS.getCODE(),
                ResultCode.DATA_ALREADY_EXISTS.getMESSAGE());

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(409));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentsException(IllegalArgumentException il) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                false,
                ResultCode.NOT_ACCEPTABLE.getCODE(),
                il.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(406));
    }


    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleDatabaseConnectionException(MyBatisSystemException my) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                false,
                ResultCode.NETWORK_ERROR.getCODE(),
                ResultCode.NETWORK_ERROR.getMESSAGE());

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(501));
    }


    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleSQLSyntaxErrorException(BadSqlGrammarException sql ) {
        logger.error(sql.getMessage());
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                false,
                ResultCode.SQL_SYNTAX_ERROR.getCODE(),
                ResultCode.SQL_SYNTAX_ERROR.getMESSAGE());

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(500));
    }



    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleBadRequest(GeneralException.MissingParameters du) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                false,
                ResultCode.BAD_REQUEST.getCODE(),
                ResultCode.BAD_REQUEST.getMESSAGE());

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(400));
    }



    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleUndefinedException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                false,
                ResultCode.INTERNAL_SERVER_ERROR.getCODE(),
                ResultCode.INTERNAL_SERVER_ERROR.getMESSAGE());

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(500));
    }




}
