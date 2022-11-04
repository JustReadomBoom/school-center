package com.zqz.school.dao.req;

import com.zqz.school.dao.bean.BasePage;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: QueryStudentPageReq
 * @Date: Created in 17:48 2022-11-3
 */
@Data
public class QueryStudentPageReq extends BasePage implements Serializable {
    private static final long serialVersionUID = -765769684524460828L;

    private String name;


}
