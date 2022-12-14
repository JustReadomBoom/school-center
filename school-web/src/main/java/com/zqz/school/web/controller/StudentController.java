package com.zqz.school.web.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqz.school.common.bean.BaseResult;
import com.zqz.school.common.enums.ApiExceptionEnum;
import com.zqz.school.common.utils.DateUtil;
import com.zqz.school.common.utils.ExcelUtil;
import com.zqz.school.dao.bean.StudentPage;
import com.zqz.school.dao.entity.ClassInfo;
import com.zqz.school.dao.entity.Student;
import com.zqz.school.dao.req.ExcelStudentData;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.InputStream;
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
    private static final int BATCH_COUNT = 10;


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
                    dataResp.setSex("???");
                } else {
                    dataResp.setSex("???");
                }
                dataList.add(dataResp);
            });
            exportNewModel(dataList, response);
        } catch (Exception e) {
            log.error("??????????????????:{}", e.getMessage());
        }
    }

    private void exportNewModel(List<StudentExcelDataResp> exportList, HttpServletResponse response) {
        OutputStream bos = null;
        String fileName = DateUtil.getTime2MSString(new Date()) + ".xls";
        try {
            bos = new BufferedOutputStream(response.getOutputStream());
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
            /**
             * ???????????????,???????????????????????? ExcelConsumListResp ??????????????????????????????,????????????????????????????????????
             */
            String[] atrArray = {"??????", "??????", "????????????", "????????????", "??????", "??????", "??????", "????????????", "???????????????", "??????????????????", "????????????", "????????????", "???????????????", "??????????????????", "????????????", "????????????"};
            //???????????????????????????,????????????
            ExcelUtil.exportExcelList(fileName, atrArray, exportList, bos);
            //?????????
            ResponseEntity.ok().body(bos);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (null != bos) {
                    bos.close();
                }
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
            log.info("????????????URL:{}", url);
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
            return new BaseResult<>(ApiExceptionEnum.FAIL.getCode(), e.getMessage());
        }
        return new BaseResult<>(uploadResp);
    }


    @PostMapping("/importData")
    public BaseResult importData(MultipartFile file) {
        InputStream in = null;
        try {
            in = file.getInputStream();
            List<Student> dataList = new ArrayList<>();
            ExcelReader excelReader = EasyExcel.read(in).build();
            ReadListener<ExcelStudentData> listener = new AnalysisEventListener<ExcelStudentData>() {
                @Override
                public void invoke(ExcelStudentData excelStudentData, AnalysisContext analysisContext) {
                    Student student = new Student();
                    BeanUtils.copyProperties(excelStudentData, student);
                    student.setCTime(DateUtil.parse2yyyyMMddHHmmss(new Date()));
                    student.setUTime(DateUtil.parse2yyyyMMddHHmmss(new Date()));
                    dataList.add(student);
                    if (dataList.size() >= BATCH_COUNT) {
                        studentService.addBatch(dataList);
                        dataList.clear();
                    }
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                    studentService.addBatch(dataList);
                }
            };
            ReadSheet sheet = EasyExcel.readSheet(0).head(ExcelStudentData.class).registerReadListener(listener).build();
            excelReader.read(sheet);
            excelReader.finish();
        } catch (Exception e) {
            log.error("importData error:{}", e.getMessage(), e);
            return new BaseResult(ApiExceptionEnum.FAIL);
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (Exception pe) {
                pe.printStackTrace();
            }
        }
        return new BaseResult(ApiExceptionEnum.SUCCESS);
    }
}
