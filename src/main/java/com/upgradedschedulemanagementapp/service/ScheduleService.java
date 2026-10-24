package com.upgradedschedulemanagementapp.service;

import com.upgradedschedulemanagementapp.dto.GetOneScheduleResponseDto;
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
    @Transactional(readOnly = true)
    public GetOneScheduleResponseDto getOneSchedule(Long id) {
        // 1. Repository에 일정 ID를 전달하여 조회 요청
        // findById()는 Optional<Schedule>을 반환한다
        // 일정이 존재하면 Schedule 객체를 꺼내고
        // 존재하지 않으면 orElseThrow()로 예외를 발생시킨다
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없는 일정 입니다.")
        );
        // 2. 조회된 Schedule Entity에서 필요한 값을 가져와 Response DTO 생성
        GetOneScheduleResponseDto responseDto = new GetOneScheduleResponseDto(
                schedule.getId(),
                schedule.getName(),
                schedule.getTitle(),
                schedule.getDetail(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
        // 3. 생성한 Response DTO를 Controller에 반환
        return responseDto;
    }


    // 일정 전체 조회

    // 일정 수정

    // 일정 삭제
}
