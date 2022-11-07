package com.zqz.school.dao.req;

import com.zqz.school.dao.bean.BasePage;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: QueryClassPageReq
 * @Date: Created in 16:20 2022-11-7
 */
@Data
public class QueryClassPageReq extends BasePage implements Serializable {

    private static final long serialVersionUID = -2104047398307120340L;

    private String classCode;

    private String className;

    private String chargeTeacher;
}
