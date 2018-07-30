package com.lph.excloudapiexchange.service.impl;

import com.lph.excloudapiexchange.entity.User;
import com.lph.excloudapiexchange.mapper.UserMapper;
import com.lph.excloudapiexchange.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Cacheable(value = "user", key = "#id")
    public User getUserById(Long id) {
        System.out.println("no cache");
        return userMapper.selectById(id);
    }
}
