package com.zqz.school.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: zqz
 * @Description:
 * @Date: Created in 13:55 2020/12/9
 */
@AllArgsConstructor
@Getter
public enum ApiExceptionEnum {
    SUCCESS("0000", "成功"),
    INVALID_REQUEST_ERROR("1001", "无效请求"),
    INVALID_PUBLIC_PARAM("1002", "参数不合法"),
    EMPTY_DATA("1003", "无数据"),
    PWD_ERROR("1004", "密码错误"),
    FAIL("1005", "失败"),
    BIND_STUDENT("1006", "该班级有挂载学生, 不允许删除!"),
    SYSTEM_ERROR("9999", "系统异常");

    private String code;
    private String msg;


}
