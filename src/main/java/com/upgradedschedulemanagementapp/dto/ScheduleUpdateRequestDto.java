package com.upgradedschedulemanagementapp.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {
    private final String title;
    private final String detail;

    public ScheduleUpdateRequestDto(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }
}
