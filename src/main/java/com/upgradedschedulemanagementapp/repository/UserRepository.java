package com.upgradedschedulemanagementapp.repository;

import com.upgradedschedulemanagementapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
 * [ UserRepository 역할 ]
 * User Entity에 대한 DB 접근을 담당한다.
 *
 * User : 관리할 Entity 타입
 * Long : User의 PK 타입
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
