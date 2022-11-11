package com.zqz.school.dao.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: StudentExcelDataResp
 * @Date: Created in 15:15 2022-11-11
 */
@Data
public class StudentExcelDataResp implements Serializable {
    private static final long serialVersionUID = -8404048366214972422L;

    private String code;
    private String name;
    private String classCode;
    private Integer age;
    private String sex;
    private String photo;
    private String fatherName;
    private String fatherPhone;
    private String fatherIdNo;
    private String fatherJob;
    private String motherName;
    private String motherPhone;
    private String motherIdNo;
    private String motherJob;
    private String homeAddress;
}
