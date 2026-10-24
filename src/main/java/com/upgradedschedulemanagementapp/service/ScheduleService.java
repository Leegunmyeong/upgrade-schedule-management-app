package com.upgradedschedulemanagementapp.service;

import com.upgradedschedulemanagementapp.dto.*;
import com.upgradedschedulemanagementapp.entity.Schedule;
import com.upgradedschedulemanagementapp.exception.ScheduleNotFoundException;
import com.upgradedschedulemanagementapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
                () -> new ScheduleNotFoundException("없는 일정 입니다.")
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
    @Transactional(readOnly = true)
    public ScheduleListResponseDto getAllSchedules() {

        // 1. 수정일(updateAt) 기준 내림차순으로 전체 일정 조회
        // Sort.by(): 어떤 필드를 기준으로 정렬할지 지정
        // Sort.Direction.DESC: 내림차순(최신 -> 오래된 순서)
        // "updatedAt": 정렬 기준이 되는 Entity 필드 이름
        List<Schedule> scheduleList =
                scheduleRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt"));

        // 2. 조회한 일정들을 DTO로 변환하여 DTO 타입으로 저장할 빈 리스트 생성
        List<ScheduleDTO> scheduleDtoList = new ArrayList<>();

        // 3. 조회한 일정들이 담겨있는 scheduleList을 for문을 사용해서
        // 하나씩 꺼내기
        for (Schedule schedule : scheduleList) {
            // 4. Schedule Entity의 정보를 ScheduleDTO에 담기
            ScheduleDTO scheduleDTO = new ScheduleDTO(
                    schedule.getId(),
                    schedule.getName(),
                    schedule.getTitle(),
                    schedule.getDetail(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt()
            );
            // 5. 생성한 DTO를 리스트에 추가
            scheduleDtoList.add(scheduleDTO);
        }
        // 6. DTO 목록을 반환할 Response DTO에 담기
        ScheduleListResponseDto scheduleListResponseDto = new ScheduleListResponseDto(scheduleDtoList);
        // 7. Controller에게 결과 반환
        return scheduleListResponseDto;
    }

    // 일정 수정

    // 일정 삭제
}
