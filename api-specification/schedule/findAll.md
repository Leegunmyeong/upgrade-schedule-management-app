# 일정 전체 조회 API

## 1. API 기본 정보
- **기능:** 일정 전체 조회
- **HTTP Method:** `GET`
- **URL:** `http://localhost:8080/schedules`
- **Status:** `200 OK`
- **인증 방식** Cookie/Session
- **인증 필요 여부** 필수(로그인 사용자)
- **요청 데이터 형식** 없음
- **응답 데이터 형식** `JSON`
- **정렬 기준:** 최종 수정일(updatedAt) 기준 내림차순(DESC)

## 2. Request Header

| Header       | 값                | 필수 여부 | 설명                             |
|--------------|-------------------|-----------|----------------------------------|
| Cookie       | JSESSIONID=세션ID | 필수      | 로그인 시 발급된 세션 쿠키 전달  |

- 로그인 성공 시 서버에서 발급한 세션 쿠키를 사용
- 클라이언트가 세션 쿠키를 전달하면 서버는 로그인한 유저를 확인
- JSESSIONID는 기본 세션 쿠키 이름, 서버 설정에 따라 달라질 수 있다
- Request Body 없기 때문에 Content-Type은 필수 ❌

#### Request URL 예시
```http 
GET http://localhost:8080/schedules
```

- 특정 일정 ID를 전달하지 않는다.
- 서버는 로그인한 유저의 일정 목록을 조회한다.

## 3. Path Variable / Query Parameter 명세

- Path Variable: 없음
- Query Parameter: 없음

## 4. Request JSON
- 없음

## 5. Response Header

| Header       | 값               | 설명                               |
|--------------|------------------|------------------------------------|
| Content-Type | application/json | 응답 데이터가 JSON 형식임을 나타냄 |

## 6. Response Body 명세
> 최상위 응답 객체

| 필드명       | JAVA 타입              | JSON  | 필수 여부 | 설명             |
|--------------|------------------------|-------|-----------|------------------|
| scheduleList | List<ScheduleResponse> | Array | 필수      | 조회된 일정 목록 |                

> scheduleList 내부 데이터 

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
- 하나의 일정은 반드시 한 명의 유저에 속해야 한다
- JPA에서는 Schedule(N) -> User(1) 방향의 @ManyToOne 단방향 연관관계를 사용한다
- Schedule 객체가 User 객체를 참조하며, User 객체에서는 Schedule 목록을 직접 참조 ❌
- PK/FK는 DB 컬럼의 제약조건이며, Response JSON의 필드 자체에 적용되는 것은 아니다

## 7. Response JSON
```json
{
  "scheduleList": [
    {
      "id": 2,
      "userId": 2,
      "name": "홍길동",
      "title": "코드카타",
      "detail": "매일매일 프로그래머스 문제 풀기",
      "createdAt": "2026-10-21T10:00:00",
      "updatedAt": "2026-10-21T10:00:00"
    },
    {
      "id": 1,
      "userId": 2,
      "name": "홍길동",
      "title": "Spring 공부",
      "detail": "일정 관리 앱 과제 진행하기",
      "createdAt": "2026-10-21T09:00:00",
      "updatedAt": "2026-10-21T09:00:00"
    }
  ]
}
```
#### 조회 결과 없는 경우
```json
{
  "scheduleList": []
}
```
- 조회 결과가 없어도 200 OK를 반환한다.
- 조회된 일정 목록을 Response DTO에 담아 반환한다.
- Entity를 직접 반환하지 않는다.
- 작성 유저명은 연관된 User 정보에서 가져온다.
- 유저 비밀번호는 응답에 포함하지 않는다.
- 날짜와 시간은 ISO 8601 형식의 문자열로 반환한다.
- 조회된 일정은 최종 수정일(updatedAt) 기준 내림차순으로 정렬한다.

## 8. 상태 코드 및 예외 상황

| HTTP 상태 코드   | 발생 상황        | 설명                                   |
|------------------|------------------|----------------------------------------|
| 401 Unauthorized | 로그인 인증 실패 | 로그인하지 않았거나 세션이 만료된 경우 |

- 조회된 일정이 없는 것은 오류가 아니라서 404 Not Found를 반환하지 않는다
- 조회 결과가 없으면 200 OK와 빈 scheduleList를 반환한다
- 로그인 유저의 일정만 조회하기 때문에 다른 유저의 일정 접근 권한 검사는 수행하지 않는다

## 9. 처리 조건
1. 일정 전체 조회는 로그인한 사용자만 가능
2. 서버는 로그인 세션에서 현재 유저 ID를 확인한다
3. JPA를 활용하여 로그인 유저의 ID에 해당하는 일정 목록을 데이터베이스에서 조회한다
4. 조회된 일정은 updatedAt 기준 내림차순(DESC)으로 정렬한다.
5. 조회된 일정들을 Response DTO 목록으로 변환한다
6. Response DTO 목록을 scheduleList에 담아 반환한다
7. 조회 결과 없을 시 빈 리스트와 200 OK를 반환한다
8. 조회 성공 시 Response DTO와 200 OK를 반환한다
9. Entity를 직접 반환하지 않는다
10. 조회 기능이기 때문에 일정의 작성일,수정일을 변경하지 않는다
11. 비밀번호 등 민감한 유저 정보는 응답에 포함하지 않는다
