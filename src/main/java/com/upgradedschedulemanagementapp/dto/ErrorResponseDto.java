package com.upgradedschedulemanagementapp.dto;

// 클라이언트에게 보여줄 오류 정보

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponseDto {
    private final String message;
    private final int status;

    public ErrorResponseDto(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
