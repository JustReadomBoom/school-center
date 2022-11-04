package com.zqz.school.dao.service;

import com.zqz.school.dao.entity.User;
import com.zqz.school.dao.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: UserService
 * @Date: Created in 15:24 2022-11-3
 */
@Service
public class UserService {

    @Resource
    private UserMapper mapper;

    public User queryByName(String userName) {
        return mapper.queryByName(userName);
    }

}
