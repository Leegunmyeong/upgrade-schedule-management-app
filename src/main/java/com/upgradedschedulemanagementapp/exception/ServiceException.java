package com.upgradedschedulemanagementapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

// 예외가 발생했을 때 사용할 HTTP 상태 코드와 오류 메세지를 관리
// 오류 정보 보관

@Getter
public class ServiceException extends RuntimeException {

    // 1. 예외 발생 시 사용할 HTTP 상태 코드를 저장
    private final HttpStatus status;

    // 2. 생성자 작성
    public ServiceException(HttpStatus status, String message) {
        // 부모 클래스에 message 전달
        // 전달받은 status를 자신의 필드에 저장
        super(message);
        this.status = status;
    }
}
