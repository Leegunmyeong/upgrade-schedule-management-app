package com.upgradedschedulemanagementapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 작성일과 수정일 자동 관리 기능 활성화
@SpringBootApplication
public class UpgradedScheduleManagementAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpgradedScheduleManagementAppApplication.class, args);
    }

}
