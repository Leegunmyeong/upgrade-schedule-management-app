package com.upgradedschedulemanagementapp.dto;

import lombok.Getter;

import java.util.List;

// 여러 명의 유저 정보가 담긴 리스트를 반환하는 DTO

@Getter
public class UserListResponseDto {
    private final List<UserDTO> userList;

    public UserListResponseDto(List<UserDTO> userList) {
        this.userList = userList;
    }
}
