package com.zqz.school.dao.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: LoginReq
 * @Date: Created in 15:59 2022-11-3
 */
@Data
public class LoginReq implements Serializable {

    private static final long serialVersionUID = -9048064288733374801L;

    private String userName;

    private String password;
}
