package com.upgradedschedulemanagementapp.controller;

import com.upgradedschedulemanagementapp.dto.GetOneUserResponseDto;
import com.upgradedschedulemanagementapp.dto.UserCreateRequestDto;
import com.upgradedschedulemanagementapp.dto.UserCreateResponseDto;
import com.upgradedschedulemanagementapp.dto.UserListResponseDto;
import com.upgradedschedulemanagementapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * [ UserController 역할 ]
 * 클라이언트 요청을 받아 Service에 전달하고,
 * 처리 결과를 HTTP 응답으로 반환한다.
 */

// 이 클래스를 REST API Controller로 등록
// 반환한 객체는 JSON 형태로 변환되어 클라이언트에게 전달
@RestController

// final 필드를 매개변수로 받는 생성자를 자동 생성
// Spring이 UserService 객체를 생성자 주입
@RequiredArgsConstructor

// 이 Controller의 공통 요청 URL을 /users로 설정
@RequestMapping("/users")
public class UserController {

    // 유저 관련 비즈니스 로직을 처리할 Service
    private final UserService userService;

    // 유저 생성 API
    @PostMapping
    // 1. JSON 요청을 DTO로 변환하고 필수 입력값 검증
    public ResponseEntity<UserCreateResponseDto> createdUser(@Valid @RequestBody UserCreateRequestDto requestDto) {

        // 2. Service에 사용자 생성 요청
        // 3. 생성된 사용자 정보를 Response DTO로 받기
        UserCreateResponseDto responseDto = userService.createdUser(requestDto);

        // 4. DTO와 HTTP 201 Created 상태 코드로 응답 생성
        ResponseEntity<UserCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);

        // 5. 클라이언트에게 응답 반환
        return response;
    }

    // 사용자 단건 조회
    @GetMapping("/{userId}")
    public ResponseEntity<GetOneUserResponseDto> getOneUser(@PathVariable Long userId) {
        // 1. URL에서 userId 값을 가져옴
        // 2. Service에 유저 ID를 전달하여 조회 요청
        GetOneUserResponseDto responseDto = userService.getOneUser(userId);
        // 3. 조회한 DTO와 HTTP 200 OK로 응답 객체 생성
        ResponseEntity<GetOneUserResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        // 4. 클라이언트에게 응답 반환
        return response;
    }

    // 사용자 전체 조회
    @GetMapping
    public ResponseEntity<UserListResponseDto> getAllUsers() {

        // 1. Service에게 모든 유저 조회 요청
        // 2. 조회 결과를 응답 DTO로 받기
        UserListResponseDto responseDto = userService.getAllUsers();

        // 3. 조회 결과와 HTTP 200 OK로 응답 객체 생성
        ResponseEntity<UserListResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        // 4. 클라이언트에게 응답 반환
        return response;
    }

    // 사용자 이메일 수정


    // 사용자 삭제
}