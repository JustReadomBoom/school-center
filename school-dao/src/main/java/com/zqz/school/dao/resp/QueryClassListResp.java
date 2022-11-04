package com.zqz.school.dao.resp;

import com.zqz.school.dao.entity.ClassInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: QueryClassListResp
 * @Date: Created in 11:21 2022-11-4
 */
@Data
public class QueryClassListResp implements Serializable {

    private static final long serialVersionUID = 3388794367910497999L;

    private List<ClassInfo> classList;
}
