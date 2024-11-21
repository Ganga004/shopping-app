package com.microservice.productservice.exception;

import com.microservice.productservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestSerivceExceptionHandler extends ResponseEntityExceptionHandler {

 @ExceptionHandler/*(ProductCustomException.class)*/
 public ResponseEntity<ErrorResponse> handleProductServiceException(ProductCustomException productCustomException) {
     return new ResponseEntity<>(ErrorResponse.builder()
             .errorCode(productCustomException.getErrorCode())
             .errorMessage(productCustomException.getMessage()).
             build(), HttpStatus.NOT_FOUND);
 }
}
