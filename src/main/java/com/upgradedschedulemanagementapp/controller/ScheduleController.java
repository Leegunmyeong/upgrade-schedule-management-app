package com.upgradedschedulemanagementapp.controller;

import com.upgradedschedulemanagementapp.dto.GetOneScheduleResponseDto;
import com.upgradedschedulemanagementapp.dto.ScheduleCreateRequestDto;
import com.upgradedschedulemanagementapp.dto.ScheduleCreateResponseDto;
import com.upgradedschedulemanagementapp.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
* [ 역할 ]
* 클라이언트의 HTTP 요청을 받고
* Service에 처리를 요청한 뒤 HTTP 응답을 반환한다
* */

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    // 일정 관련 비즈니스 로직을 처리할 Service
    // @RequiredArgsConstructor가 생성자를 만들어 의존성을 주입받음
    private final ScheduleService scheduleService;

    // 일정 생성
    // 생성이기 때문에 @PostMapping 사용
    @PostMapping
    public ResponseEntity<ScheduleCreateResponseDto> createdSchedule(@Valid @RequestBody ScheduleCreateRequestDto request) {
        // 1. 클라이언트가 전달한 Request DTO를 Service에 전달
        // 2. Service가 반환한 Response DTO를 변수에 저장
        ScheduleCreateResponseDto responseDto = scheduleService.createdSchedule(request);
        // 3. Response DTO와 201 Created 상태 코드로 HTTP 응답 생성
        ResponseEntity<ScheduleCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        // 4. 생성된 HTTP 응답을 클라이언트에게 반환
        return response;
    }

    // 일정 단건 조회
    // 조회이기 때문에 @GetMapping 사용
    @GetMapping("/{scheduleId}")
    public ResponseEntity<GetOneScheduleResponseDto> getOneSchedule(@PathVariable Long scheduleId) {

        // 1. URL에서 전달받은 일정 ID를 Service에 전달
        // 2. Service가 반환한 Response DTO를 변수에 저장
        GetOneScheduleResponseDto responseDto = scheduleService.getOneSchedule(scheduleId);

        // 3. Response DTO와 200 OK 상태 코드로 HTTP 응답 생성
        ResponseEntity<GetOneScheduleResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        // 4. 생성된 HTTP 응답을 클라이언트에게 반환
        return response;
    }

    // 일정 전체 조회

    // 일정 수정

    // 일정 삭제
}
