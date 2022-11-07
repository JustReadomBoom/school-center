package com.zqz.school.dao.resp;

import com.zqz.school.dao.bean.BasePageInfo;
import com.zqz.school.dao.entity.ClassInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: QueryClassPageResp
 * @Date: Created in 16:20 2022-11-7
 */
@Data
public class QueryClassPageResp extends BasePageInfo implements Serializable {
    private static final long serialVersionUID = -1711754134109841209L;

    private List<ClassInfo> classInfos;
}
