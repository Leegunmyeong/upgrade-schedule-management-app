package com.upgradedschedulemanagementapp.exception;


import org.springframework.http.HttpStatus;

// 일정이 존재하지 않을 때 발생시키는 커스텀 예외
public class ScheduleNotFoundException extends ServiceException{

    // 1. 예외 메세지를 전달 받음
    public ScheduleNotFoundException(String message) {
        // 2. 부모 ServiceException에 404와 메세지 전달
        super(HttpStatus.NOT_FOUND, message);
    }
}
