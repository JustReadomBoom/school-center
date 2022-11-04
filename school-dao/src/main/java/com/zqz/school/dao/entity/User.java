package com.zqz.school.dao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: User
 * @Date: Created in 15:22 2022-11-3
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 6555756891988373495L;

    private Integer id;

    private String userName;

    private String password;

    private Integer disable;

    private String cTime;
}
