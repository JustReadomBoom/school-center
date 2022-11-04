package com.zqz.school.dao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: ClassInfo
 * @Date: Created in 14:30 2022-10-31
 */
@Data
public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 2978563970612133454L;
    private Integer id;
    private String classCode;
    private String className;
    private String classLogo;
    private String chargeTeacher;
    private String cTime;
    private String uTime;

}
