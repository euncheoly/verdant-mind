package com.zeroinon.chatterboard.service;

import com.zeroinon.chatterboard.dto.UserDTO;

public interface UserService {

    void register(UserDTO userProfile);

    UserDTO login(String id, String password);

    boolean isDuplicateId(String id);

    UserDTO getUserInfo(String userId);

    void updatePassword(String id, String oldPassword, String newPassword);

    void deleteId(String id, String password);




}
