package com.upgradedschedulemanagementapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleDTO {

    private final Long id;
    private final String name;
    private final String title;
    private final String detail;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ScheduleDTO(Long id, String name, String title, String detail, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.detail = detail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
