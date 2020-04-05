//package com.bw.exception;
//
//import com.bw.apiclient.ApiResponse;
//import lombok.Data;
//import org.springframework.http.HttpStatus;
//
///**
// * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
// */
//@Data
//public class ErrorResponse extends RuntimeException {
//
//    private final ApiResponse<?> apiResponse;
//
//    public ErrorResponse(ApiResponse<?> apiResponse) {
//        this.apiResponse = apiResponse;
//    }
//
//    public ErrorResponse(HttpStatus code, String message, Object data) {
//        this(new ApiResponse<>(code.value(), message, data));
//    }
//
//    public ErrorResponse(HttpStatus code, String message) {
//        this(new ApiResponse<>(code.value(), message));
//    }
//
//    public ErrorResponse(int code, String message, Object data) {
//        this(new ApiResponse<>(code, message, data));
//    }
//
//    public ErrorResponse(int code, String message) {
//        this(new ApiResponse<>(code, message));
//    }
//}
