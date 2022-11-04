package com.zqz.school.dao.mapper;

import com.zqz.school.dao.entity.User;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: UserMapper
 * @Date: Created in 15:23 2022-11-3
 */
public interface UserMapper {

    User queryByName(String userName);

}
