package com.zeroinon.chatterboard.service;

import com.zeroinon.chatterboard.dto.response.GenericResponseDTO;
import com.zeroinon.chatterboard.dto.UserDTO;

import java.util.Map;

public interface UserService {

    GenericResponseDTO register(UserDTO userProfile);

    GenericResponseDTO login(UserDTO userCredentials);

    boolean isDuplicateId(String id);

    GenericResponseDTO getMemberInfo(int id);

    GenericResponseDTO hashGetMemberInfo(int id);

    Map<String, String> getMemberInfoFromLocalCache(int id);

    GenericResponseDTO updatePassword(UserDTO userDTO);

    void deleteId(String id, String password);




}
