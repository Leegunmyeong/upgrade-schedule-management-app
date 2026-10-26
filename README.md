# 일정 관리 애플리케이션

## 1. 프로젝트 소개

Spring Boot와 JPA를 활용하여 User와 Schedule을 관리하는 REST API 프로젝트입니다.

- User 및 Schedule CRUD 구현
- JPA Auditing을 이용한 작성일·수정일 관리
- User와 Schedule의 N:1 연관관계
- Cookie/Session을 이용한 로그인

## 2. 기술 스택

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Gradle

## 3. ERD

![ERD](images/upgrade%20%EC%9D%BC%EC%A0%95%20%EA%B4%80%EB%A6%AC%20%EC%95%B1%20.png)

## 4. API 명세서

### User API

- [유저 생성](api-specification/user/create.md)
- [유저 단건 조회](api-specification/user/findOne.md)
- [유저 전체 조회](api-specification/user/findAll.md)
- [유저 수정](api-specification/user/update.md)
- [유저 삭제](api-specification/user/delete.md)

### Schedule API

- [일정 생성](api-specification/schedule/create.md)
- [일정 단건 조회](api-specification/schedule/findOne.md)
- [일정 전체 조회](api-specification/schedule/findAll.md)
- [일정 수정](api-specification/schedule/update.md)
- [일정 삭제](api-specification/schedule/delete.md)

### Auth API

- [로그인](api-specification/auth/login.md)
- [로그아웃](api-specification/auth/logout.md)

## 5. 테이블 명세서

- [User 테이블](api-specification/table/user.md)
- [Schedule 테이블](api-specification/table/schedule.md)
