package com.zeroinon.chatterboard.service.impl;

import com.zeroinon.chatterboard.dto.UserDTO;
import com.zeroinon.chatterboard.mapper.UserMapper;
import com.zeroinon.chatterboard.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public String test(){
        return userMapper.test();
    }

    @Override
    public void register(UserDTO userProfile) {

    }

    @Override
    public UserDTO login(String id, String password) {
        return null;
    }

    @Override
    public boolean isDuplicateId(String id) {
        return false;
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
