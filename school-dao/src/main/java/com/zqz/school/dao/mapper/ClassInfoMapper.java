package com.zqz.school.dao.mapper;

import com.zqz.school.dao.entity.ClassInfo;
import com.zqz.school.dao.req.QueryClassPageReq;

import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: ClassInfoMapper
 * @Date: Created in 14:38 2022-10-31
 */
public interface ClassInfoMapper {

    ClassInfo queryByCode(String code);

    List<ClassInfo> queryClassList();

    List<ClassInfo> queryByParam(QueryClassPageReq req);

    int deleteById(Integer id);

    ClassInfo queryById(Integer id);

    int update(ClassInfo classInfo);

}
