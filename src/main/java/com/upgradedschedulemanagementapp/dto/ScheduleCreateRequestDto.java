package com.upgradedschedulemanagementapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {
    // 일정을 작성하는 유저의 ID
    @NotNull(message = "유저 ID를 입력해주세요")
    private Long userId;
    @NotBlank(message = "일정 제목을 입력해주세요")
    private String title;
    @NotBlank(message = "일정 내용을 입력해주세요")
    private String detail;
}
