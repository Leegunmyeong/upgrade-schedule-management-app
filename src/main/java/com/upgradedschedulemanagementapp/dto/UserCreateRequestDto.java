package com.upgradedschedulemanagementapp.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserCreateRequestDto {

    // 이름이 null, 빈 문자열, 공백이면 검증 실패
    @NotBlank(message = "유저명을 입력해주세요.")
    private String name;
    // 이메일이 null, 빈 문자열, 공백이면 검증 실패
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
}
