package com.zqz.school.dao.mapper;

import com.zqz.school.dao.entity.Student;
import com.zqz.school.dao.req.QueryStudentPageReq;

import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: StudentMapper
 * @Date: Created in 14:39 2022-10-31
 */
public interface StudentMapper {

    Student queryByName(String name);

    List<Student> queryByParam(QueryStudentPageReq req);

    Student queryByCode(String code);

    int add(Student student);

    int deleteById(Integer id);

    int update(Student student);

    List<Student> queryByClassCode(String classCode);
}
