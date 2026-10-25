package com.upgradedschedulemanagementapp.service;


import com.upgradedschedulemanagementapp.dto.UserCreateRequestDto;
import com.upgradedschedulemanagementapp.dto.UserCreateResponseDto;
import com.upgradedschedulemanagementapp.entity.User;
import com.upgradedschedulemanagementapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * [ UserService 역할 ]
 * 유저 관련 비즈니스 로직을 처리한다
 */

// 이 클래스를 Service 계층의 Spring Bean으로 등록
@Service
// final 필드를 사용하는 생성자 자동 생성
@RequiredArgsConstructor
public class UserService {
    // DB 작업을 담당하는 Repository 주입
    private final UserRepository userRepository;

    // 유저 생성
    // DB 저장 작업을 하나의 트랜잭션에서 처리
    @Transactional
    public UserCreateResponseDto createdUser(UserCreateRequestDto requestDto) {
        // 1. Request DTO의 이름과 이메일로 User 객체 생성
        User user = new User(
                requestDto.getName(),
                requestDto.getEmail()
        );
        // 2. Repository에 사용자 저장 요청
        // 3. 저장 결과를 User 타입 변수에 저장
        User savedUser = userRepository.save(user);
        // 4. 저장된 User 객체에서 필요한 값만 꺼내 응답 DTO 생성
        UserCreateResponseDto responseDto = new UserCreateResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt()
        );
        // 5. Controller에 응답 DTO 반환
        return responseDto;
    }

    // 단건 유저 조회

    // 모든 유저 조회

    // 유저 상세정보 수정

    // 유저 삭제
}
