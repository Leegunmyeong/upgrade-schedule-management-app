package com.upgradedschedulemanagementapp.config;

import com.upgradedschedulemanagementapp.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.sql.rowset.serial.SerialException;

// 애플리케이션에서 발생하는 에외를 공통으로 처리
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. ServiceException과 이를 상속한 예외를 처리
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> handleServiceException(ServiceException ex) {

        // 2. 예외를 저장된 상태 코드와 메세지를 클라이언트에게 변환
        ResponseEntity response = new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        return response;
    }
}
