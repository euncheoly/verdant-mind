package com.zeroinon.chatterboard.mapper;

import com.zeroinon.chatterboard.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {



    int isDuplicateId(String userId);

    int insertUser(UserDTO userDTO);


}
