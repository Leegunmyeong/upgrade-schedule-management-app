package com.upgradedschedulemanagementapp.dto;

// 일정 목록을 담는 응답 DTO

import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleListResponseDto {
    private final List<ScheduleDTO> scheduleList;


    // 회
    public ScheduleListResponseDto(List<ScheduleDTO> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
