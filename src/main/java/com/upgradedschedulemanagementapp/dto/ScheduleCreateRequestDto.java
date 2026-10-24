package com.upgradedschedulemanagementapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @NotBlank(message = "일정 제목을 입력해주세요")
    private String title;
    @NotBlank(message = "일정 내용을 입력해주세요")
    private String detail;
}
