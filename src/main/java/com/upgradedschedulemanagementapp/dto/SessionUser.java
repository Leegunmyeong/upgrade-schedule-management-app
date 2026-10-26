package com.upgradedschedulemanagementapp.dto;

import lombok.Getter;

@Getter
public class SessionUser {
    // 로그인한 유저의 고유 ID
    private final Long id;
    // 로그인한 유저의 이메일
    private final String email;


    // 전달받은 유저 ID와 이메일을 필드에 저장
    public SessionUser(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
