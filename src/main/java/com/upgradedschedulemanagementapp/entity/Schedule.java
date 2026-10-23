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


}
