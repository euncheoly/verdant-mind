package com.zeroinon.chatterboard.controller;


import com.zeroinon.chatterboard.base.constant.ResultCode;
import com.zeroinon.chatterboard.base.dto.GenericResponseDTO;
import com.zeroinon.chatterboard.dto.UserDTO;
import com.zeroinon.chatterboard.exception.GeneralException;
import com.zeroinon.chatterboard.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.MissingResourceException;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {


    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @RequestMapping("/registration")
    public GenericResponseDTO userRegistration(HttpServletRequest req
                                    , HttpServletResponse resp,
                                               @RequestBody UserDTO userDTO) {
        if(UserDTO.hasNullParameterForUserRegister(userDTO)){
            throw new GeneralException.MissingParameters(ResultCode.BAD_REQUEST.getMESSAGE());
        }
        return userServiceImpl.register(userDTO);
    }


}
