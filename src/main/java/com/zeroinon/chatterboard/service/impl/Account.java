package com.zeroinon.chatterboard.service.impl;


import com.zeroinon.chatterboard.dto.response.GenericResponseDTO;
import com.zeroinon.chatterboard.dto.UserDTO;
import com.zeroinon.chatterboard.exception.GeneralException;
import com.zeroinon.chatterboard.exception.UserException;
import com.zeroinon.chatterboard.mapper.UserMapper;
import com.zeroinon.chatterboard.service.JwtService;
import com.zeroinon.chatterboard.service.UserService;
import com.zeroinon.chatterboard.utils.BCryptUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.zeroinon.chatterboard.config.CacheConfig.CACHE1;
import static com.zeroinon.chatterboard.config.CacheConfig.CACHE2;

@Service
public class Account implements UserService{


    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final RedisTemplate<String, Object> redisTemplate;

    public Account(UserMapper userMapper, JwtService jwtService, RedisTemplate<String, Object> redisTemplate) {
        this.userMapper = userMapper;
        this.jwtService = jwtService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public GenericResponseDTO register(UserDTO userProfile) {

        boolean duplicateResult = isDuplicateId(userProfile.getUserId());
        if (duplicateResult) {
            throw new UserException.DuplicatedUserID("Duplicated User ID");
        }
        userProfile.setPassword(BCryptUtils.bcryptHash(userProfile.getPassword()));

        int registerResult = userMapper.insertUser(userProfile);
        if (registerResult != 1) {
            throw new RuntimeException("Register failed");
        }
        return GenericResponseDTO.of(registerResult);
    }

    @Override
    public GenericResponseDTO login(UserDTO userDTO) {

        String hashed = userMapper.findHashByUserid(userDTO);

        if(hashed == null || !BCrypt.checkpw(userDTO.getPassword(), hashed)){
            throw new UserException.InvalidPassword("Invalid Password");
        }

        JwtService.AccountRole accountRole = userMapper.isAdminPrivileged(userDTO.getUserId()) == 1
                ? JwtService.AccountRole.ADMIN : JwtService.AccountRole.USER;

        String access = jwtService.generateToken(userDTO.getUserId(), JwtService.TokenRole.ACCESS, accountRole);
        String refresh = jwtService.generateToken(userDTO.getUserId(), JwtService.TokenRole.REFRESH, accountRole);
        HashMap jwt = new HashMap();
        jwt.put("access", access);
        jwt.put("refresh", refresh);
        return GenericResponseDTO.of(jwt);
    }


    @Override
    public boolean isDuplicateId(String id) {
        boolean isDuplicateId = userMapper.isDuplicateId(id) > 0 ? true : false;
        System.out.println("isDuplicateId: " + isDuplicateId);
        return isDuplicateId;
    }


    @Override
    public GenericResponseDTO updatePassword(UserDTO userDTO) {
        System.out.println("updating password.....");
        return null;
    }

    @Override
    public void deleteId(String id, String password) {
    }

    @Override
    public GenericResponseDTO getMemberInfo(int id) {
        Map<String, String> memberInfo = new HashMap<>();
        String memberInfoRedisKey =  "users:%d".formatted(id);
        memberInfo = (Map<String, String>) redisTemplate.opsForValue().get(memberInfoRedisKey);
        if (memberInfo != null && memberInfo.size() != 0) {
            return GenericResponseDTO.of(memberInfo);
        }
        memberInfo = userMapper.getMemberInfo(id);
        if (memberInfo == null) {
            throw new GeneralException.RequestDataUnavailable("Member Not Found");
        }
        redisTemplate.opsForValue().set(memberInfoRedisKey, memberInfo, Duration.ofSeconds(30));
        return GenericResponseDTO.of(memberInfo);
    }



    @Override
    public GenericResponseDTO hashGetMemberInfo(int id) {
        String memberInfoRedisKey = "hash-users:%d".formatted(id);
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        Map<String, String> memberInfo = hashOperations.entries(memberInfoRedisKey);
        if (memberInfo != null && !memberInfo.isEmpty()) {
            return GenericResponseDTO.of(memberInfo);
        }
        memberInfo = userMapper.getMemberInfo(id);
        if (memberInfo == null) {
            throw new GeneralException.RequestDataUnavailable("Member Not Found");
        }
        hashOperations.putAll(memberInfoRedisKey, memberInfo);
        redisTemplate.expire(memberInfoRedisKey, Duration.ofSeconds(30));

        return GenericResponseDTO.of(memberInfo);

    }



    @Override
    @Cacheable(cacheNames = CACHE2, key = "'memberinfo:'+ #idx")
    public Map<String, String> getMemberInfoFromLocalCache(int idx) {
        Map<String, String> memberInfo = userMapper.getMemberInfo(idx);
        if (memberInfo == null) {
            throw new GeneralException.RequestDataUnavailable("Member Not Found");
        }
        return memberInfo;
    }



}
