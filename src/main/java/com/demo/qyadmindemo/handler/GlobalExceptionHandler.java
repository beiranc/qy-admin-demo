package com.demo.qyadmindemo.handler;

import com.demo.qyadmindemo.response.ResponseModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常捕获
 */
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseModel handlerException(Exception e) {
        return ResponseModel.error(e.getLocalizedMessage(), e.getStackTrace().toString());
    }
}
