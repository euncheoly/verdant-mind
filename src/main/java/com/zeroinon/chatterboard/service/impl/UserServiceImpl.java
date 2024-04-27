package com.zeroinon.chatterboard.service.impl;

import com.zeroinon.chatterboard.dto.response.GenericResponseDTO;
import com.zeroinon.chatterboard.dto.UserDTO;
import com.zeroinon.chatterboard.exception.UserException;
import com.zeroinon.chatterboard.mapper.UserMapper;
import com.zeroinon.chatterboard.service.UserService;
import com.zeroinon.chatterboard.utils.SHA256Utils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public GenericResponseDTO register(UserDTO userProfile) {

        boolean duplicateResult = isDuplicateId(userProfile.getUserId());
        if (duplicateResult) {
            throw new UserException.DuplicatedUserID("Duplicated User ID");
        }

        userProfile.setPassword(SHA256Utils.encrypt(userProfile.getPassword()));
        userProfile.setWithdrawnUser(false);

        int registerResult = userMapper.insertUser(userProfile);
        if (registerResult != 1) {
            throw new RuntimeException("Register failed");
        }
        return GenericResponseDTO.of(registerResult);
    }

    @Override
    public UserDTO login(String id, String password) {
        return null;
    }

    @Override
    public boolean isDuplicateId(String id) {
        boolean isDuplicateId = userMapper.isDuplicateId(id) > 0 ? true : false;
        System.out.println("isDuplicateId: " + isDuplicateId);
        return isDuplicateId;
    }

    @Override
    public UserDTO getUserInfo(String userId) {
        return null;
    }

    @Override
    public void updatePassword(String id, String oldPassword, String newPassword) {
    }

    @Override
    public void deleteId(String id, String password) {
    }
}
