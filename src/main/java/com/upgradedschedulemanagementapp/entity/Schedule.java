package com.upgradedschedulemanagementapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    // 속성
    // 1. 일정 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 2. 작성 유저명
    @Column(nullable = false)
    private String name;

    // 3. 할일 제목
    @Column(nullable = false)
    private String title;

    // 4. 할일 내용
    @Column(nullable = false)
    private String detail;

    public Schedule(String name, String title, String detail) {
        this.name = name;
        this.title = title;
        this.detail = detail;
    }

    // 수정 기능
    public void update(String title, String detail) {

        // 1. 제목이 전달됐다면 제목 수정
        if (title != null) {
            this.title = title;
        }

        // 2. 내용이 전달됐다면 내용 수정
        if (detail != null) {
            this.detail = detail;
        }


    }

}
