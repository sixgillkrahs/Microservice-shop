package com.main.common.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.main.common.exception.ErrorCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ResponseAPI<T>{
    int code;
    int state;
    String message;
    T data;

    public static <T> ResponseAPI<T> successResponse(T data) {
        ResponseAPI<T> response = new ResponseAPI<>();
        response.setCode(200);
        response.setMessage("Success");
        response.setState(2);
        response.setData(data);
        return response;
    }

    public static <T> ResponseAPI<T> errorResponse(ErrorCode errorCode) {
        ResponseAPI<T> response = new ResponseAPI<>();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        response.setState(9999);
        response.setData(null);
        return response;
    }
}

