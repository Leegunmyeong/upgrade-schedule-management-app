# 일정 생성 API

## 1. API 기본 정보
- **기능:** 일정 생성
- **HTTP Method:** `POST`
- **URL:** `http://localhost:8080/schedules`
- **Status:** `201 Created`
- **인증 방식** Cookie/Session
- **인증 필요 여부** 필수(로그인 사용자)
- **요청 데이터 형식** `JSON`
- **응답 데이터 형식** `JSON`

## 2. Request Header

| Header       | 값                | 필수 여부 | 설명                             |
|--------------|-------------------|-----------|----------------------------------|
| Content-Type | application/json  | 필수      | 요청 데이터를 JSON 형식으로 전달 |
| Cookie       | JSESSIONID=세션ID | 필수      | 로그인 시 발급된 세션 쿠키 전달  |

- 로그인 성공 시 서버에서 발급한 세션 쿠키를 사용
- 클라이언트가 세션 쿠키를 전달하면 서버는 로그인한 유저를 확인
- JSESSIONID는 기본 세션 쿠키 이름, 서버 설정에 따라 달라질 수 있다

## 3. Request Body 명세

| 필드명 | JAVA 타입 | JSON   | 필수 여부 | 설명      |
|--------|-----------|--------|-----------|-----------|
| title  | String    | String | 필수      | 일정 제목 |
| detail | String    | String | 필수      | 할일 내용 |

## 4. Request JSON
```json
{
  "title": "Spring 공부",
  "detail": "일정 관리 앱 과제 진행"
}
```
- 작성 유저명(name)과 유저 ID(userId)는 요청 본문에 포함 ❌
- 서버는 로그인 세션에서 유저 정보를 확인하여 일정 작성자를 설정한다.
- 일정 ID는 서버에서 자동 생성 하기 때문에 본문에 포함 ❌

## 5. Response Header

| Header       | 값               | 설명                               |
|--------------|------------------|------------------------------------|
| Content-Type | application/json | 응답 데이터가 JSON 형식임을 나타냄 |

## 6. Response Body 명세

| 필드명    | JAVA 타입     | JSON             | 필수 여부 | DB 매핑                | 설명                             | 
|-----------|---------------|------------------|-----------|------------------------|----------------------------------|
| id        | Long          | Number           | 필수      | schedules.id (PK)      | 일정 고유 식별자                 |
| userId    | Long          | Number           | 필수      | schedules.user_id (FK) | 일정을 작성한 유저의 고유 식별자 | 
| name      | String        | String           | 필수      | users.name             | 작성 유저명                      | 
| title     | String        | String           | 필수      | schedules.title        | 일정 제목                        | 
| detail    | String        | String           | 필수      | schedules.detail       | 할일 내용                        |
| createdAt | LocalDateTime | String(DateTime) | 필수      | schedules.created_at   | 최초 작성일                      |
| updatedAt | LocalDateTime | String(DateTime) | 필수      | schedules.updated_at   | 최종 수정일                      | 

### DB 연관관계
- schedules.id: 일정 테이블 PK
- users.id: 유저 테이블 PK
- schedules.user_id(FK): users.id(PK)를 참조
- name: 연결된 User 객체의 유저명을 조회한 값

#### 연관관계 설명
- User(1) : Schedule(0..N) 관계이다
- 하나의 유저는 0개 이상의 일정을 작성할 수 있다
- 하나의 일정은 반드시 한 명의 유저에 속해야한다
- JPA에서는 Schedule(N) -> User(1) 방향의 @ManyToOne 단방향 연관관계를 사용한다
- Schedule 객체가 User 객체를 참조하며, User 객체에서는 Schedule 목록을 직접 참조 ❌ 
- PK/FK는 DB 컬럼의 제약조건이며, Response JSON의 필드 자체에 적용되는 것은 아니다

## 7. Response JSON
```json
{
  "id": 1,
  "userId": 2,
  "name": "홍길동",
  "title": "Spring 공부",
  "detail": "일정 관리 앱 과제 진행",
  "createdAt": "2026-10-21T09:00:00",
  "updatedAt": "2026-10-21T09:00:00"
}
```

- 일정 생성 성공 시 Response DTO에 담아 반환한다.
- Entity를 직접 반환하지 않는다.
- 작성 유저명은 연관된 User 정보에서 가져온다.
- 유저 비밀번호는 응답에 포함하지 않는다.
- 날짜와 시간은 ISO 8601 형식의 문자열로 반환한다.

## 8. 상태 코드 및 예외 상황

| HTTP 상태 코드   | 발생 상황             | 설명                                      |
|------------------|-----------------------|-------------------------------------------|
| 400 Bad Request  | 요청 데이터 검증 실패 | 제목이나 내용이 누락되거나 비어 있는 경우 |
| 401 Unauthorized | 로그인 인증 실패      | 로그인하지 않았거나 세션이 만료된 경우    |

## 9. 처리 조건
1. 일정 생성은 로그인한 사용자만 가능
2. 일정 제목과 내용은 필수 입력값
3. 일정 ID는 데이터베이스에서 자동 생성
4. 서버는 현재 로그인한 유저를 확인하여 작성자로 설정
5. schedules.user_id는 users.id를 참조
6. 작성일과 수정일은 JPA Auditing으로 자동 관리
7. 일정 생성 시 작성일과 수정일은 생성 시점으로 기록
8. 생성된 일정은 JPA를 활용하여 데이터베이스에 저장
9. 생성 성공 시 Response DTO와 201 Created를 반환
10. Entity를 직접 반환 ❌
11. 비밀번호 등 민감한 유저 정보는 응답에 포함 ❌