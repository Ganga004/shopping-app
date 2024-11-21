package com.microservice.productservice.exception;

import lombok.Data;

@Data
public class ProductCustomException extends RuntimeException{
    public String errorCode;

    public ProductCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
