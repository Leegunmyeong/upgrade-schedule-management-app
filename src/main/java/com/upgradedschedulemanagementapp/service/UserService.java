package com.upgradedschedulemanagementapp.service;


import com.upgradedschedulemanagementapp.dto.*;
import com.upgradedschedulemanagementapp.entity.User;
import com.upgradedschedulemanagementapp.exception.UserNotFoundException;
import com.upgradedschedulemanagementapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
                requestDto.getEmail(),
                requestDto.getPw()
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
    @Transactional(readOnly = true)
    public GetOneUserResponseDto getOneUser(Long userId) {

        // 1. 전달받은 userId를 이용해 DB에서 유저 조회
        // 2. 유저가 존재하지 않으면 UserNotFoundException 발생
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("없는 유저 입니다.")
        );
        // 3. 조회한 User Entity의 정보를 응답 DTO에 담기
        GetOneUserResponseDto responseDto = new GetOneUserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        // 4. Controller에게 응답 DTO 반환
        return responseDto;
    }

    // 모든 유저 조회
    @Transactional(readOnly = true)
    public UserListResponseDto getAllUsers() {
        // 1. 수정일 기준 내림차순으로 모든 유저 조회
        // DB에서 조회한 Entity 목록
        List<User> userList = userRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt"));
        // 2. 유저 정보를 담을 빈 DTO 리스트 생성
        // 클라이언트에게 전달할 DTO 목록
        List<UserDTO> userDtoList  = new ArrayList<>();
        // 3. 조회한 User Entity를 하나씩 꺼냄
        for (User user : userList) {
            // 4. User Entity의 정보를 UserDto로 변환
            UserDTO userDTO = new UserDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getUpdatedAt()
            );
            // 5. 변환한 DTO를 UserDTO 타입의 객체를 담는 리스트에 추가
            userDtoList.add(userDTO);
        }
        // 6. DTO 리스트를 전체 조회 응답 DTO에 저장
        UserListResponseDto responseDto = new UserListResponseDto(userDtoList);
        // 7. Controller에게 응답 DTO 반환
        return responseDto;
    }

    // 유저 상세정보 수정
    @Transactional
    public UserUpdateResponseDto updatedUser(Long id, UserUpdateRequestDto requestDto) {
        // 1. ID로 기존 유저 조회
        // 2. 존재하지 않으면 404 예외 발생
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("없는 유저 입니다.")
        );

        // 3. Request DTO에 수정할 이메일 가져오기
        String email = requestDto.getEmail();

        // 4. User Entity의 이메일 변경
        user.update(email);

        // 5. 수정된 User의 정보를 응답 DTO로 변환
        UserUpdateResponseDto responseDto = new UserUpdateResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        // 6. Controller에 반환
        return responseDto;
    }

    // 유저 삭제
    @Transactional
    public void deletedUser(Long userId) {
        // 1. userId를 사용해서 삭제할 유저 조회
        // 2. 유저가 없으면 404 예외 발생
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("없는 유저 입니다.")
        );
        // 3. 조회한 User Entity 삭제
        userRepository.delete(user);

    }

}
