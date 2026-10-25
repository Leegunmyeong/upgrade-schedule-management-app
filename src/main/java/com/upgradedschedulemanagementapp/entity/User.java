package com.upgradedschedulemanagementapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * [ User Entity 역할 ]
 * Db의 users 테이블에 저장할 사용자 정보를 관리
 */

@Getter
// 이 클래스를 JPA Entity로 등록
@Entity
// 연결할 DB 테이블 이름 지정
@Table(name = "users")
// JPA가 사용할 기본 생성자 생성
// 외부에서 함부로 호출하지 못하도록 protected 접근 제한
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{

    // 유저 고유 식발자(PK)
    @Id
    // DB에서 ID를 자동 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name; // 사용자 이름 / null 저장 불가
    @Column(unique = true, nullable = false)
    private String email; // 사용자 이메일 / 중복 값 불가 및 null 저장 불가

    // 새로운 User 객체 생성 시 이름과 이메일 초기화
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // 유저 업데이트 기능
    public void update(String email) {
        this.email = email;
    }

}
