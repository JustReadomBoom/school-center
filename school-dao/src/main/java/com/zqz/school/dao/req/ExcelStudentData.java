package com.zqz.school.dao.req;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: ExcelStudentData
 * @Date: Created in 10:46 2022-11-18
 */
@Data
public class ExcelStudentData implements Serializable {

    private static final long serialVersionUID = -5226624313047134015L;

    @ExcelProperty(index = 0)
    private String code;

    @ExcelProperty(index = 1)
    private String name;

    @ExcelProperty(index = 2)
    private Integer age;

    @ExcelProperty(index = 3)
    private Integer sex;

    @ExcelProperty(index = 4)
    private String fatherName;

    @ExcelProperty(index = 5)
    private String fatherPhone;

    @ExcelProperty(index = 6)
    private String fatherIdNo;

    @ExcelProperty(index = 7)
    private String fatherJob;

    @ExcelProperty(index = 8)
    private String motherName;

    @ExcelProperty(index = 9)
    private String motherPhone;

    @ExcelProperty(index = 10)
    private String motherIdNo;

    @ExcelProperty(index = 11)
    private String motherJob;

    @ExcelProperty(index = 12)
    private String homeAddress;
}
