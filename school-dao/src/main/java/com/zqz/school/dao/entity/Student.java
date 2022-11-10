package com.zqz.school.dao.entity;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: Student
 * @Date: Created in 14:30 2022-10-31
 */
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = -7231177764076928290L;
    private Integer id;
    private String code;
    private String name;
    private String classCode;
    private Integer age;
    private Integer sex;
    private String photo;
    private String fatherName;

    private String fatherPhone;

    private String fatherJob;

    private String motherName;

    private String motherPhone;

    private String motherJob;
    private String homeAddress;
    private String cTime;
    private String uTime;

}
