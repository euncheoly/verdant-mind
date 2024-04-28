package com.zeroinon.chatterboard.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

    public enum AccountStatus {
        ACTIVE,
        DORMANT,
        BLOCKED,
        TERMINATED
    }

    private int id;
    private String userId;
    private String password;
    private String newPassword;
    private String newPasswordConfirm;
    private String alias;
    private int role;
    private AccountStatus accountStatus;


    public static boolean hasNullParameterForUserRegister(UserDTO userDTO) {
        return userDTO.getUserId() == null ||
                userDTO.getPassword() == null ||
                userDTO.getAlias() == null;
    }

    public static boolean hasNullParameterForLogin(UserDTO userDTO) {
        return userDTO.getUserId() == null ||
                userDTO.getPassword() == null;
    }

    public static boolean hasNullParameterForChangePassword(UserDTO userDTO) {
        return userDTO.getUserId() == null ||
                userDTO.getPassword() == null ||
                userDTO.getNewPassword() == null ||
                userDTO.getNewPasswordConfirm() == null;
    }


}
