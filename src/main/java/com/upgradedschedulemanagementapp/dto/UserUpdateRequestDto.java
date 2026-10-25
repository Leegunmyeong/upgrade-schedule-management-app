package com.upgradedschedulemanagementapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {

    @NotBlank(message = "이메일을 입력해주세요")
    private String email;
}
