package com.upgradedschedulemanagementapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
* [역할]
* User와 Schedule에서 공통으로 사용하는
* 작성일(createdAt), 수정일(updatedAt)을 관리하는 클래스
* */


// Lombok이 Getter 메서드를 자동 생성
// 자식 Entity가 부모의 필드를 물려받도록 함
@Getter

// 자식 Entity가 날짜 필드를 상속받도록 하려고 사용
// 일정 저장, 수정 시 Auditing 기능이 작동하도록 연결
@MappedSuperclass

// Entity 저장, 수정 시 Auditing 기능 실행
@EntityListeners(AuditingEntityListener.class)

public class BaseEntity {

    // 속성

    // 최초 작성일을 자동으로 저장
    // 최초 작성일은 UPDATE 대상에 포함되지 않음
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 마지막 수정일을 자동으로 저장
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
