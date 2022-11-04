package com.zqz.school.dao.resp;

import com.zqz.school.common.bean.BasePageInfo;
import com.zqz.school.dao.bean.StudentPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: QueryStudentPageResp
 * @Date: Created in 17:43 2022-11-3
 */
@Data
public class QueryStudentPageResp extends BasePageInfo implements Serializable {
    private static final long serialVersionUID = 29697852546438656L;

    private List<StudentPage> students;

}
