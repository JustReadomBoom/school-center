package com.zqz.school.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqz.school.common.bean.BaseResult;
import com.zqz.school.common.enums.ApiExceptionEnum;
import com.zqz.school.common.utils.DateUtil;
import com.zqz.school.dao.entity.ClassInfo;
import com.zqz.school.dao.entity.Student;
import com.zqz.school.dao.req.QueryClassPageReq;
import com.zqz.school.dao.resp.QueryClassListResp;
import com.zqz.school.dao.resp.QueryClassPageResp;
import com.zqz.school.dao.service.ClassInfoService;
import com.zqz.school.dao.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: ClassInfoController
 * @Date: Created in 11:20 2022-11-4
 */
@RestController
@RequestMapping("class")
public class ClassInfoController {
    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/queryClassList")
    public BaseResult<QueryClassListResp> queryClassList() {
        QueryClassListResp listResp = new QueryClassListResp();
        List<ClassInfo> classInfos = classInfoService.queryClassList();
        if (CollectionUtils.isEmpty(classInfos)) {
            return new BaseResult<>(ApiExceptionEnum.EMPTY_DATA);
        }
        listResp.setClassList(classInfos);
        return new BaseResult<>(listResp);
    }


    @PostMapping("/queryPage")
    public BaseResult<QueryClassPageResp> queryPage(@RequestBody QueryClassPageReq req) {
        QueryClassPageResp pageResp = new QueryClassPageResp();
        Integer currentPage = req.getCurrentPage();
        Integer pageSize = req.getPageSize();
        Page<Object> page = PageHelper.startPage(currentPage, pageSize);
        List<ClassInfo> classInfos = classInfoService.queryByParam(req);
        if (CollectionUtils.isEmpty(classInfos)) {
            return new BaseResult<>(ApiExceptionEnum.EMPTY_DATA);
        }
        pageResp.setClassInfos(classInfos);
        pageResp.setTotal(page.getTotal());
        pageResp.setPages(page.getPages());
        return new BaseResult<>(pageResp);
    }

    @GetMapping("/deleteById")
    public BaseResult deleteById(@RequestParam("id") Integer id) {
        ClassInfo classInfo = classInfoService.queryById(id);
        if (ObjectUtils.isEmpty(classInfo)) {
            return new BaseResult(ApiExceptionEnum.FAIL);
        }
        String classCode = classInfo.getClassCode();
        List<Student> students = studentService.queryByClassCode(classCode);
        if (!CollectionUtils.isEmpty(students)) {
            return new BaseResult(ApiExceptionEnum.BIND_STUDENT);
        }
        int d = classInfoService.deleteById(id);
        if (d > 0) {
            return new BaseResult(ApiExceptionEnum.SUCCESS);
        }
        return new BaseResult(ApiExceptionEnum.FAIL);
    }


    @PostMapping("/update")
    public BaseResult update(@RequestBody ClassInfo classInfo) {
        int u = classInfoService.update(classInfo);
        if (u > 0) {
            return new BaseResult(ApiExceptionEnum.SUCCESS);
        }
        return new BaseResult(ApiExceptionEnum.FAIL);
    }


    @PostMapping("/add")
    public BaseResult add(@RequestBody ClassInfo classInfo) {
        classInfo.setCTime(DateUtil.parse2yyyyMMddHHmmss(new Date()));
        classInfo.setUTime(DateUtil.parse2yyyyMMddHHmmss(new Date()));
        int add = classInfoService.add(classInfo);
        if (add > 0) {
            return new BaseResult(ApiExceptionEnum.SUCCESS);
        }
        return new BaseResult(ApiExceptionEnum.FAIL);
    }

}
