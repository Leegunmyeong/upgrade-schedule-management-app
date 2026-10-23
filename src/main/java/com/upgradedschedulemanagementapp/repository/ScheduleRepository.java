package com.upgradedschedulemanagementapp.repository;

import com.upgradedschedulemanagementapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

/*
* [역할]
* Schedule Entity를 데이터베이스에 저장하고 조회하는 역할
*
* JpaRepository를 상속받아 기본적인 CRUD 메서드를 사용한다
*
* 관리할 Entity: Schedule
* Schedule의 PK 타입: Long
* */

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
