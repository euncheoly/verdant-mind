package com.zeroinon.chatterboard.mapper;

import com.zeroinon.chatterboard.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    String findHashByUserid(UserDTO userDTO);

    int isDuplicateId(String userId);

    int insertUser(UserDTO userDTO);

    int isAdminPrivileged(String userId);

    List<UserDTO> findAllUsers();


}
