package com.zeroinon.chatterboard.mapper;

import com.zeroinon.chatterboard.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {



    int isDuplicateId(String userId);

    int insertUser(UserDTO userDTO);


}
