package com.zqz.school.web.controller;

import com.zqz.school.common.bean.BaseResult;
import com.zqz.school.common.enums.ApiExceptionEnum;
import com.zqz.school.dao.req.LoginReq;
import com.zqz.school.dao.entity.User;
import com.zqz.school.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: UserController
 * @Date: Created in 15:49 2022-11-3
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public BaseResult login(@RequestBody LoginReq req) {
        String userName = req.getUserName();
        String password = req.getPassword();
        User user = userService.queryByName(userName);
        if (ObjectUtils.isEmpty(user)) {
            return new BaseResult(ApiExceptionEnum.EMPTY_DATA);
        }
        if (!password.equals(user.getPassword())) {
            return new BaseResult(ApiExceptionEnum.PWD_ERROR);
        }
        return new BaseResult(ApiExceptionEnum.SUCCESS);
    }
}
