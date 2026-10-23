package com.upgradedschedulemanagementapp.service;

import com.upgradedschedulemanagementapp.dto.ScheduleCreateRequestDto;
import com.upgradedschedulemanagementapp.dto.ScheduleCreateResponseDto;
import com.upgradedschedulemanagementapp.entity.Schedule;
import com.upgradedschedulemanagementapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // 일정 생성 기능
    @Transactional
    public ScheduleCreateResponseDto createdSchedule(ScheduleCreateRequestDto requestDto) {
        // 1. Request DTO에 담긴 값으로 Schedule 객체 생성
        Schedule schedule = new Schedule(
                requestDto.getName(),
                requestDto.getTitle(),
                requestDto.getDetail()
        );
        // 2. Repository에 Schedule 객체 저장 요청
        // save()의 반환 타입도 Schedule이므로 Schedule 변수로 받음
        Schedule savedSchedule = scheduleRepository.save(schedule);
        // 3. 저장한 Schedule 객체에서 필요한 값을 꺼내 Response DTO 생성
        ScheduleCreateResponseDto responseDto = new ScheduleCreateResponseDto(
                savedSchedule.getId(),
                savedSchedule.getName(),
                savedSchedule.getTitle(),
                savedSchedule.getDetail(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
        // 4. 생성한 Response DTO를 Controller에 반환
        return responseDto;
    }

    // 일정 단건 조회

    // 일정 전체 조회

    // 일정 수정

    // 일정 삭제
}
