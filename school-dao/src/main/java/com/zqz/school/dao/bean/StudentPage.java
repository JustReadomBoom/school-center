package com.zqz.school.dao.bean;

import com.zqz.school.dao.entity.Student;
import lombok.Data;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: StudentPage
 * @Date: Created in 15:31 2022-11-4
 */
@Data
public class StudentPage extends Student {

    private static final long serialVersionUID = 7317096263480309027L;


    private String className;
}
