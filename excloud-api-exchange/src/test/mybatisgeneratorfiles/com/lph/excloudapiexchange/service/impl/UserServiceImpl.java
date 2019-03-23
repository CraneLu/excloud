package com.lph.excloudapiexchange.service.impl;

import com.lph.excloudapiexchange.entity.User;
import com.lph.excloudapiexchange.mapper.UserMapper;
import com.lph.excloudapiexchange.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lph
 * @since 2018-07-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
