# 일정 단건 조회 API

## 1. API 기본 정보
- **기능:** 일정 단건 조회
- **HTTP Method:** `GET`
- **URL:** `http://localhost:8080/schedules/{scheduleId}`
- **Status:** `200 OK`
- **인증 방식** Cookie/Session
- **인증 필요 여부** 필수(로그인 사용자)
- **요청 데이터 형식** PathVariable (Request Body 없음)
- **응답 데이터 형식** `JSON`

## 2. Request Header

| Header       | 값                | 필수 여부 | 설명                             |
|--------------|-------------------|-----------|----------------------------------|
| Cookie       | JSESSIONID=세션ID | 필수      | 로그인 시 발급된 세션 쿠키 전달  |

- 로그인 성공 시 서버에서 발급한 세션 쿠키를 사용
- 클라이언트가 세션 쿠키를 전달하면 서버는 로그인한 유저를 확인
- JSESSIONID는 기본 세션 쿠키 이름, 서버 설정에 따라 달라질 수 있다
- Request Body 없기 때문에 Content-Type은 필수 ❌

## 3. PathVariable 명세

| 변수명     | JAVA 타입 | 필수 여부 | 설명                      |
|------------|-----------|-----------|---------------------------|
| scheduleId | Long      | 필수      | 조회할 일정의 고유 식별자 |

#### Request URL 예시
```http request
GET http://localhost:8080/schedules/1
```

- scheduleId는 조회할 일정의 ID를 의미
- 일정 ID는 URL의 PathVariable을 통해 전달한다
- Request Body는 사용하지 않는다


## 4. Request JSON
- 없음

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

- 조회 성공 시 Response DTO에 담아 반환한다.
- Entity를 직접 반환하지 않는다.
- 작성 유저명은 연관된 User 정보에서 가져온다.
- 유저 비밀번호는 응답에 포함하지 않는다.
- 날짜와 시간은 ISO 8601 형식의 문자열로 반환한다.

## 8. 상태 코드 및 예외 상황

| HTTP 상태 코드   | 발생 상황             | 설명                                      |
|------------------|-----------------------|-------------------------------------------|
| 400 Bad Request  | 요청 데이터 검증 실패 | scheduleId를 Long으로 변환할 수 없는 경우 |
| 401 Unauthorized | 로그인 인증 실패      | 로그인하지 않았거나 세션이 만료된 경우    |
| 403 Forbidden    | 접근 권한 없음        | 다른 유저가 작성한 일정에 접근한 경우     |
| 404 Not Found    | 일정 조회 실패        | 해당 ID의 일정이 존재하지 않는 경우       |

## 9. 처리 조건
1. 일정 조회는 로그인한 사용자만 가능
2. 조회할 일정 ID는 PathVariable로 전달한다
3. 서버는 로그인 세션에서 현재 유저 ID를 확인한다
4. JPA를 활용하여 전달받은 일정 ID로 데이터베이스를 조회한다
5. 일정 미존재 시 404 Not Found 반환
6. 조회된 일정 작성 유저와 로그인 유저 일치하는지 확인
7. 다른 유저의 일정이면 403 Forbidden을 반환
8. 조회 성공 시 ResponseDTO와 200 OK을 반환한다
9. Entity를 직접 반환 ❌
10. 조회 기능이기 때문에 일정의 작성일과 수정일은 변경 ❌
11. 비밀번호 등 민감한 유저 정보는 응답에 포함 ❌