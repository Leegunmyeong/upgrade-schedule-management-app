package com.upgradedschedulemanagementapp.dto;
/*
 * 역할
 * 로그인할 때 필요한 이메일과 비밀번호를 전달받는다
 */

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String pw;
}
