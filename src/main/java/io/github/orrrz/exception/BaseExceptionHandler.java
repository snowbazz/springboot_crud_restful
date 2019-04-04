package io.github.orrrz.exception;

import io.github.orrrz.entity.Result;
import io.github.orrrz.entity.StatusCode;
import org.omg.CORBA.SystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by icejam.
 * 全局异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public Result customHandler(SystemException e) {
        return new Result(false, StatusCode.ERROR, "系统异常");
    }

    // 其他未处理的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
