package com.zqz.school.common.bean;

import com.zqz.school.common.enums.ApiExceptionEnum;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: BaseResult
 * @Date: Created in 16:36 2022-9-7
 */
@Data
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = 8506426168245708612L;

    private String code;
    private String msg;

    private T data;

    public BaseResult(T data) {
        super();
        this.code = ApiExceptionEnum.SUCCESS.getCode();
        this.msg = ApiExceptionEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public BaseResult(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(Exception e, String msg) {
        super();
        this.msg = ObjectUtils.isEmpty(e.getMessage()) ? msg : e.getMessage();
        this.code = ApiExceptionEnum.SYSTEM_ERROR.getCode();
    }

    public BaseResult(ApiExceptionEnum apiExceptionEnum) {
        super();
        this.code = apiExceptionEnum.getCode();
        this.msg = apiExceptionEnum.getMsg();
    }
}
