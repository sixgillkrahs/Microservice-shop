package com.main.common.exception;

import com.main.common.response.ResponseAPI;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@FieldDefaults(level = AccessLevel.PUBLIC)
public class GlobalHandleException extends RuntimeException {

    @ExceptionHandler(value = HandleRuntimeException.class)
    ResponseEntity<ResponseAPI> handleRuntimeException(HandleRuntimeException e){
        ErrorCode errorCode = e.getErrorCode();
        ResponseAPI response = new ResponseAPI();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(response);
    }




}
