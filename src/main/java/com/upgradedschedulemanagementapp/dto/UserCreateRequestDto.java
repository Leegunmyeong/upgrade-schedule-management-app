package com.upgradedschedulemanagementapp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateRequestDto {

    // 이름이 null, 빈 문자열, 공백이면 검증 실패
    @NotBlank(message = "유저명을 입력해주세요.")
    private String name;
    // 이메일이 null, 빈 문자열, 공백이면 검증 실패
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    // 비밀번호는 최소 8글자 이상
    @Size(min = 8, message = "비밀번호는 8글자 이상이어야 합니다.")
    private String pw;
}
