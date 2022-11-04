package com.zqz.school.dao.service;

import com.zqz.school.dao.entity.ClassInfo;
import com.zqz.school.dao.mapper.ClassInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: ClassInfoService
 * @Date: Created in 10:21 2022-11-1
 */
@Service
public class ClassInfoService {
    @Resource
    private ClassInfoMapper mapper;

    public ClassInfo queryByCode(String code) {
        return mapper.queryByCode(code);
    }

    public List<ClassInfo> queryClassList(){
        return mapper.queryClassList();
    }

}
