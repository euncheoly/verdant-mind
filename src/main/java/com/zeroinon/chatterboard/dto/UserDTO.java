package com.zeroinon.chatterboard.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserDTO {

    public enum Status{
        DEFAULT,
        ADMIN,
        DELETED
    }

    private int id;
    private String userId;
    private String password;
    private String alias;
    private boolean isAdmin;
    private boolean isWithdrawnUser;
    private Status userStatus;


    public static boolean hasNullParameterForUserRegister(UserDTO userDTO){
        return  userDTO.getUserId() == null ||
                userDTO.getPassword() == null ||
                userDTO.getAlias() == null;


    }


}
