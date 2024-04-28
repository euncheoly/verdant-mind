package com.zeroinon.chatterboard.mapper;

import com.zeroinon.chatterboard.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    String findHashByUserid(UserDTO userDTO);

    int isDuplicateId(String userId);

    int insertUser(UserDTO userDTO);


}
