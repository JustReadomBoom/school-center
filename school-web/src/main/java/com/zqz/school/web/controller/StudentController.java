package com.zqz.school.web.controller;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqz.school.common.bean.BaseResult;
import com.zqz.school.common.enums.ApiExceptionEnum;
import com.zqz.school.common.utils.DateUtil;
import com.zqz.school.dao.bean.StudentPage;
import com.zqz.school.dao.entity.ClassInfo;
import com.zqz.school.dao.entity.Student;
import com.zqz.school.dao.req.QueryStudentPageReq;
import com.zqz.school.dao.resp.QueryStudentPageResp;
import com.zqz.school.dao.service.ClassInfoService;
import com.zqz.school.dao.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: StudentController
 * @Date: Created in 11:21 2022-11-1
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassInfoService classInfoService;

    @GetMapping("/queryByName")
    public Object queryByName(@RequestParam("name") String name) {
        Student student = studentService.queryByName(name);
        return JSONUtil.toJsonStr(student);
    }


    @PostMapping("/queryPage")
    public BaseResult<QueryStudentPageResp> queryPage(@RequestBody QueryStudentPageReq req) {
        QueryStudentPageResp pageResp = new QueryStudentPageResp();
        Integer currentPage = req.getCurrentPage();
        Integer pageSize = req.getPageSize();
        Page<Object> page = PageHelper.startPage(currentPage, pageSize);
        List<Student> students = studentService.queryByParam(req);
        if (CollectionUtils.isEmpty(students)) {
            return new BaseResult<>(ApiExceptionEnum.EMPTY_DATA);
        }
        List<StudentPage> respList = new ArrayList<>();
        students.forEach(s -> {
            StudentPage studentPage = new StudentPage();
            BeanUtils.copyProperties(s, studentPage);
            studentPage.setClassName(Optional.ofNullable(classInfoService.queryByCode(s.getClassCode())).map(ClassInfo::getClassName).orElse(null));
            respList.add(studentPage);
        });
        pageResp.setStudents(respList);
        pageResp.setTotal(page.getTotal());
        pageResp.setPages(page.getPages());
        return new BaseResult<>(pageResp);
    }

    @GetMapping("/queryByCode")
    public BaseResult<Student> queryByCode(@RequestParam("code") String code) {
        Student student = studentService.queryByCode(code);
        if (ObjectUtils.isEmpty(student)) {
            return new BaseResult<>(ApiExceptionEnum.EMPTY_DATA);
        }
        return new BaseResult<>(student);
    }


    @PostMapping("/add")
    public BaseResult add(@RequestBody Student student) {
        student.setCTime(DateUtil.parse2yyyyMMddHHmmss(new Date()));
        student.setUTime(DateUtil.parse2yyyyMMddHHmmss(new Date()));
        int add = studentService.add(student);
        if (add > 0) {
            return new BaseResult(ApiExceptionEnum.SUCCESS);
        }
        return new BaseResult(ApiExceptionEnum.SYSTEM_ERROR);
    }


    @GetMapping("/deleteById")
    public BaseResult deleteById(@RequestParam("id") Integer id) {
        int d = studentService.deleteById(id);
        if (d > 0) {
            return new BaseResult(ApiExceptionEnum.SUCCESS);
        }
        return new BaseResult(ApiExceptionEnum.FAIL);
    }

    @PostMapping("/update")
    public BaseResult update(@RequestBody Student student){
        int u = studentService.update(student);
        if (u > 0) {
            return new BaseResult(ApiExceptionEnum.SUCCESS);
        }
        return new BaseResult(ApiExceptionEnum.FAIL);
    }



}
