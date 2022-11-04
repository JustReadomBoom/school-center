package com.zqz.school.dao.service;

import com.zqz.school.dao.bean.StudentPage;
import com.zqz.school.dao.entity.Student;
import com.zqz.school.dao.mapper.StudentMapper;
import com.zqz.school.dao.req.QueryStudentPageReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: StudentService
 * @Date: Created in 10:23 2022-11-1
 */
@Service
public class StudentService {
    @Resource
    private StudentMapper mapper;

    public Student queryByName(String name) {
        return mapper.queryByName(name);
    }


    public List<Student> queryByParam(QueryStudentPageReq req) {
        return mapper.queryByParam(req);
    }

    public Student queryByCode(String code) {
        return mapper.queryByCode(code);
    }

    public int add(Student student){
        return mapper.add(student);
    }


    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }

    public int update(Student student) {
        return mapper.update(student);
    }
}
