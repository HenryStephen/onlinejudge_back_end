package cn.edu.nciae.onlinejudge.commons.business;


import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局业务异常处理
 * <p>
 * Description:
 * </p>
 *
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/15 20:44
 * @see cn.edu.nciae.onlinejudge.commons.business
 */
@Slf4j
@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handlerException(HttpServletRequest request, Exception ex) {
        ResponseResult error = new ResponseResult();

        // 业务异常
        if (ex instanceof BusinessException) {
            error.setCode(((BusinessException) ex).getCode());
            error.setMessage(ex.getMessage());
            log.warn("[全局业务异常]\r\n业务编码：{}\r\n异常记录：{}", error.getCode(), error.getMessage());
        }

        // 未知错误
        else {
            error.setCode(BusinessStatus.UNKNOWN.getCode());
            error.setMessage(BusinessStatus.UNKNOWN.getMessage());
        }

        return new ResponseEntity<>(error, HttpStatus.OK);
    }

}
