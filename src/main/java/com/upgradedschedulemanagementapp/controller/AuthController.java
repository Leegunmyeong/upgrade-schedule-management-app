package com.upgradedschedulemanagementapp.controller;

/*
 * [역할]
 * 로그인과 로그아웃 요청을 받고
 * Service에 인증 처리를 요청한다
 */


import com.upgradedschedulemanagementapp.dto.LoginRequestDto;
import com.upgradedschedulemanagementapp.dto.SessionUser;
import com.upgradedschedulemanagementapp.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    // 로그인 검증 로직을 담당
    private final AuthService authService;

    // 로그인 기능
    // HttpSession Spring이 현재 요청에 연결된 세션 객체를 전달
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto requestDto, HttpSession session) {
        // 1. 로그인 검증 요청
        // 2. 검증 성공 시 유저 정보를 SessionUser DTO로 받음
        SessionUser sessionUser = authService.login(requestDto);

        // 3. 서버 Session에 로그인 유저 정보 저장
        session.setAttribute("loginUser", sessionUser);

        // 4. 로그인 성공 메세지와 HTTP 200 OK DTO 생성
        ResponseEntity<String> response = new ResponseEntity<>("로그인 성공했습니다.", HttpStatus.OK);
        return response;
    }
}
