package com.upgradedschedulemanagementapp.controller;

import com.upgradedschedulemanagementapp.dto.ScheduleCreateRequestDto;
import com.upgradedschedulemanagementapp.dto.ScheduleCreateResponseDto;
import com.upgradedschedulemanagementapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 생성
    // 생성이기 때문에 @PostMapping 사용
    @PostMapping
    public ResponseEntity<ScheduleCreateResponseDto> createdSchedule(@RequestBody ScheduleCreateRequestDto request) {
        ScheduleCreateResponseDto responseDto = scheduleService.createdSchedule(request);
        ResponseEntity<ScheduleCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        return response;
    }

    // 일정 단건 조회

    // 일정 전체 조회

    // 일정 수정

    // 일정 삭제
}
