package com.zqz.school.web.controller;

import com.zqz.school.common.bean.BaseResult;
import com.zqz.school.common.enums.ApiExceptionEnum;
import com.zqz.school.dao.entity.ClassInfo;
import com.zqz.school.dao.resp.QueryClassListResp;
import com.zqz.school.dao.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
