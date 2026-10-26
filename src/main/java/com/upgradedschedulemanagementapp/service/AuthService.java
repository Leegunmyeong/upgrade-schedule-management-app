package com.upgradedschedulemanagementapp.service;

import com.upgradedschedulemanagementapp.dto.LoginRequestDto;
import com.upgradedschedulemanagementapp.dto.SessionUser;
import com.upgradedschedulemanagementapp.entity.User;
import com.upgradedschedulemanagementapp.exception.ServiceException;
import com.upgradedschedulemanagementapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * 역할
 * 이메일로 유저를 찾고
 * 비밀번호가 일치한지 확인하는 역할
 */


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    // 로그인 검증
    // 조회만 수행
    @Transactional(readOnly = true)
    public SessionUser login(LoginRequestDto requestDto) {
        // 1. 로그인 요청에서 이메일 가져오기
        String email = requestDto.getEmail();

        // 2. 이메일에 해당하는 유저 조회
        // 해당 이메일이 없으면 예외 발생
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ServiceException(HttpStatus.UNAUTHORIZED, "이메일이 일치하지 않습니다.")
        );

        // 3. 요청에서 비밀번호 가져오기
        String pw = requestDto.getPw();

        // 4. DB의 비밀번호와 입력한 비밀번호 같지않으면 401 예외 발생
        if (!user.getPw().equals(pw)) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // 5. 로그인에 성공한 유저의 정보를
        // SessionUser DTO에 담기
        SessionUser sessionUser = new SessionUser(
                user.getId(),
                user.getEmail()
        );

        return sessionUser;
    }

}
