package com.zqz.school.common.exp;

import com.zqz.school.common.bean.BaseResult;
import com.zqz.school.common.enums.ApiExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: GlobalExceptionHandler
 * @Date: Created in 16:16 2022-9-8
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResult paramValidException(MethodArgumentNotValidException e){
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String defaultMessage = allErrors.get(0).getDefaultMessage();
        log.error("参数错误:{}", defaultMessage);
        return new BaseResult(ApiExceptionEnum.INVALID_PUBLIC_PARAM.getCode(), defaultMessage);
    }
}
