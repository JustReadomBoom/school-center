package com.zqz.school.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqz.school.common.bean.BaseResult;
import com.zqz.school.common.enums.ApiExceptionEnum;
import com.zqz.school.common.utils.DateUtil;
import com.zqz.school.common.utils.ExcelUtil;
import com.zqz.school.dao.bean.StudentPage;
import com.zqz.school.dao.entity.ClassInfo;
import com.zqz.school.dao.entity.Student;
import com.zqz.school.dao.req.QueryStudentPageReq;
import com.zqz.school.dao.resp.QueryStudentPageResp;
import com.zqz.school.dao.resp.StudentExcelDataResp;
import com.zqz.school.dao.resp.StudentPhotoUploadResp;
import com.zqz.school.dao.service.ClassInfoService;
import com.zqz.school.dao.service.StudentService;
import com.zqz.school.service.common.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
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
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private FileService fileService;


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
    public BaseResult update(@RequestBody Student student) {
        int u = studentService.update(student);
        if (u > 0) {
            return new BaseResult(ApiExceptionEnum.SUCCESS);
        }
        return new BaseResult(ApiExceptionEnum.FAIL);
    }


    @GetMapping("/downloadData")
    public void downloadData(HttpServletResponse response) {
        List<Student> studentList = studentService.queryByParam(new QueryStudentPageReq());
        if (CollectionUtils.isEmpty(studentList)) {
            return;
        }
        try {
            List<StudentExcelDataResp> dataList = new ArrayList<>();
            studentList.forEach(s -> {
                StudentExcelDataResp dataResp = new StudentExcelDataResp();
                BeanUtils.copyProperties(s, dataResp);
                if (1 == s.getSex()) {
                    dataResp.setSex("男");
                } else {
                    dataResp.setSex("女");
                }
                dataList.add(dataResp);
            });
            exportNewModel(dataList, response);
        } catch (Exception e) {
            log.error("文件下载错误:{}", e.getMessage());
        }
    }

    private void exportNewModel(List<StudentExcelDataResp> exportList, HttpServletResponse response) {
        OutputStream bos = null;
        String fileName = DateUtil.getTime2MSString(new Date()) + ".xls";
        try {
            bos = new BufferedOutputStream(response.getOutputStream());
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
            /**
             * 这里的表头,需要与前面定义的 ExcelConsumListResp 类中的属性名保持一致,并且顺序和数量也需要一致
             */
            String[] atrArray = {"学号", "姓名", "班级编号", "年龄", "性别", "头像", "爸爸姓名", "爸爸手机号", "爸爸身份证号", "爸爸工作", "妈妈姓名", "妈妈手机号", "妈妈身份证号", "妈妈工作", "家庭地址"};
            //调用工具类中的方法,进行导出
            ExcelUtil.exportExcelList(fileName, atrArray, exportList, bos);
            //写出流
            ResponseEntity.ok().body(bos);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                bos.close();
            } catch (Exception be) {
                be.printStackTrace();
            }
        }
    }


    @PostMapping("/uploadImg")
    public BaseResult<StudentPhotoUploadResp> uploadImg(MultipartFile file, @RequestParam("id") Integer id) {
        StudentPhotoUploadResp uploadResp = new StudentPhotoUploadResp();
        try {
            String fileName = DateUtil.getTime2MSString(new Date());
            String url = fileService.upload(file, fileName);
            log.info("上传结果URL:{}", url);
            if (ObjectUtils.isEmpty(url)) {
                return new BaseResult<>(ApiExceptionEnum.FAIL);
            }
            uploadResp.setPhoto(url);
            Student student = new Student();
            student.setId(id);
            student.setPhoto(url);
            student.setUTime(DateUtil.parse2yyyyMMddHHmmss(new Date()));
            studentService.update(student);
        } catch (Exception e) {
            log.error("uploadImg error:{}", e.getMessage(), e);
        }
        return new BaseResult<>(uploadResp);
    }
}
