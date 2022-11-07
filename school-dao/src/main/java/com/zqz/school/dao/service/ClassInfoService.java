package com.zqz.school.dao.service;

import com.zqz.school.dao.entity.ClassInfo;
import com.zqz.school.dao.mapper.ClassInfoMapper;
import com.zqz.school.dao.req.QueryClassPageReq;
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

    public List<ClassInfo> queryByParam(QueryClassPageReq req){
        return mapper.queryByParam(req);
    }

    public int deleteById(Integer id){
        return mapper.deleteById(id);
    }


    public ClassInfo queryById(Integer id){
        return mapper.queryById(id);
    }

    public int update(ClassInfo classInfo){
        return mapper.update(classInfo);
    }

}
