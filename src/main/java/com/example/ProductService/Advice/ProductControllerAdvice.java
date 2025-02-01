package com.example.ProductService.Advice;

import com.example.ProductService.Exceptions.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDto> NullPointerException(){
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<ExceptionDto>(new ExceptionDto("Product Not Found"), HttpStatus.NOT_FOUND);
        return responseEntity;

    }



}
