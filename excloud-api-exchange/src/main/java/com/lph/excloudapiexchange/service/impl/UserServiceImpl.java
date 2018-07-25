package com.lph.excloudapiexchange.service.impl;

import com.lph.excloudapiexchange.entity.User;
import com.lph.excloudapiexchange.mapper.UserMapper;
import com.lph.excloudapiexchange.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }
}
