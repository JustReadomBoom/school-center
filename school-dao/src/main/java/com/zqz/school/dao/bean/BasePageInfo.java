package com.zqz.school.dao.bean;

import lombok.Data;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: BasePageInfo
 * @Date: Created in 16:03 2022-9-8
 */
@Data
public class BasePageInfo {

    private long total;

    private Integer pages;
}
