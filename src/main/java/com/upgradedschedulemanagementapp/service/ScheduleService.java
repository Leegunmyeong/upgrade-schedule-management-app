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

    // 일정 생성
    @Transactional
    public ScheduleCreateResponseDto createdSchedule(ScheduleCreateRequestDto requestDto) {
        Schedule schedule = new Schedule(
                requestDto.getName(),
                requestDto.getTitle(),
                requestDto.getDetail()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        ScheduleCreateResponseDto responseDto = new ScheduleCreateResponseDto(
                savedSchedule.getId(),
                savedSchedule.getName(),
                savedSchedule.getTitle(),
                savedSchedule.getDetail(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
        return responseDto;
    }

    // 일정 단건 조회

    // 일정 전체 조회

    // 일정 수정

    // 일정 삭제
}
