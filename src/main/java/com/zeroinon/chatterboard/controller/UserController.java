package com.zeroinon.chatterboard.controller;


import com.zeroinon.chatterboard.aop.TokenValidator;
import com.zeroinon.chatterboard.base.constant.ResultCode;
import com.zeroinon.chatterboard.dto.TokenDTO;
import com.zeroinon.chatterboard.dto.response.GenericResponseDTO;
import com.zeroinon.chatterboard.dto.UserDTO;
import com.zeroinon.chatterboard.exception.GeneralException;
import com.zeroinon.chatterboard.service.JwtService;
import com.zeroinon.chatterboard.service.impl.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {


    private final Account account;
    private final JwtService jwtService;

    public UserController(Account account, JwtService jwtService) {
        this.account = account;
        this.jwtService = jwtService;
    }

    @RequestMapping("/registration")
    public GenericResponseDTO userRegistration(HttpServletRequest req, HttpServletResponse resp,
                                               @RequestBody UserDTO userDTO) {
        if (UserDTO.hasNullParameterForUserRegister(userDTO)) {
            throw new GeneralException.MissingParameters(ResultCode.BAD_REQUEST.getMESSAGE());
        }
        return account.register(userDTO);
    }




    @RequestMapping("/login")
    public GenericResponseDTO login(HttpServletRequest req, HttpServletResponse resp,
                                    @RequestBody UserDTO userDTO) {
        if (UserDTO.hasNullParameterForLogin(userDTO)) {
            throw new GeneralException.MissingParameters(ResultCode.BAD_REQUEST.getMESSAGE());
        }
        return account.login(userDTO);
    }


    @TokenValidator
    @RequestMapping("/password-update")
    public GenericResponseDTO changePassword(HttpServletRequest req, HttpServletResponse resp,
                                             @RequestBody UserDTO userDTO) {
        if (UserDTO.hasNullParameterForChangePassword(userDTO)) {
            throw new GeneralException.MissingParameters(ResultCode.BAD_REQUEST.getMESSAGE());
        }
        return account.updatePassword(userDTO);
    }


    @RequestMapping("/tokens")
    public GenericResponseDTO reissueJwtToken(HttpServletRequest req, HttpServletResponse resp,
                                              @RequestBody TokenDTO tokenDTO) {
        if (TokenDTO.hasNullParameterForRefreshToken(tokenDTO)) {
            throw new GeneralException.MissingParameters(ResultCode.BAD_REQUEST.getMESSAGE());
        }
        return GenericResponseDTO.of(jwtService.reissueToken(tokenDTO.getRefreshToken()));
    }


}
