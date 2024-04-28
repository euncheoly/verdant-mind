package com.zeroinon.chatterboard.service;

import com.zeroinon.chatterboard.dto.response.GenericResponseDTO;
import com.zeroinon.chatterboard.dto.UserDTO;

public interface UserService {

    GenericResponseDTO register(UserDTO userProfile);

    GenericResponseDTO login(UserDTO userCredentials);

    boolean isDuplicateId(String id);

    UserDTO getUserInfo(String userId);

    GenericResponseDTO updatePassword(UserDTO userDTO);

    void deleteId(String id, String password);




}
