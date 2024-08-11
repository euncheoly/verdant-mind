package com.zeroinon.chatterboard.mapper;

import com.zeroinon.chatterboard.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.redis.core.RedisHash;

import java.util.Map;

@Mapper
public interface UserMapper {

    String findHashByUserid(UserDTO userDTO);

    int isDuplicateId(String userId);

    int insertUser(UserDTO userDTO);

    int isAdminPrivileged(String userId);

    Map<String, String>  getMemberInfo(int id);


}
