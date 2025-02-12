package com.scaler.userservice_rbac.controllerAdvise;

import com.scaler.userservice_rbac.dto.ApiResponse;
import com.scaler.userservice_rbac.exceptions.ResourceNotFoundException;
import com.scaler.userservice_rbac.exceptions.UnauthorizedException;
import com.scaler.userservice_rbac.exceptions.UserAlreadyExistInSystem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApiResponse> handleResourceNotFoundException (ResourceNotFoundException ex){
        ApiResponse newApIResponse = new ApiResponse("error","Resource not found",ex.getMessage());
        return new ResponseEntity<>(newApIResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ApiResponse> handleUnauthorizedException (UnauthorizedException ex){
        ApiResponse newApIResponse = new ApiResponse("error","Unauthorized",ex.getMessage());
        return new ResponseEntity<>(newApIResponse,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler
    public ResponseEntity<ApiResponse> handleUserAlreadyExistInSystemException (UserAlreadyExistInSystem ex){
        ApiResponse newApIResponse = new ApiResponse("error","User Exist",ex.getMessage());
        return new ResponseEntity<>(newApIResponse,HttpStatus.CONFLICT);
    }
    public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
        ApiResponse newApIResponse = new ApiResponse("error", "Internal server error", "An unexpected error occurred.");
        return new ResponseEntity<>(newApIResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
