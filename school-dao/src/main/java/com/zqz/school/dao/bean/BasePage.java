package com.zqz.school.dao.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: BasePage
 * @Date: Created in 15:21 2022-9-8
 */
@Data
public class BasePage implements Serializable {
    private static final long serialVersionUID = 7577161000414577427L;

    @NotNull(message = "分页参数currentPage不能为空")
    private Integer currentPage;

    @NotNull(message = "分页参数pageSize不能为空")
    private Integer pageSize;
}
