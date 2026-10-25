- **domain:** schedule
- **URL:** `http://localhost:8080/schedules`
- **method:** `POST`
- **인증/인가:** 없음

# 1. 설명

---

새로운 일정을 생성하는 API입니다.
- 작성자 유저 ID, 제목, 내용은 필수 입력값입니다.
- 유저 ID를 이용해 기존 유저와 일정을 연결합니다.
- 작성일과 수정일은 자동으로 저장됩니다.

# 2. 요청(Request)

---

## a. Parameter & Querystring

- 없음

## b. Request headers

| 이름         | 데이터타입 | 설명                                |
|--------------|------------|-------------------------------------|
| Content-Type | String     | 요청 데이터 형식 (application/json) |


## c. Request Body

```json
{
  "userId": 1,
  "title": "Spring 공부",
  "detail": "일정 관리 앱 과제 진행"
}
```

## d. Request Body 명세

| 이름   | 필수여부 | 데이터타입 | 설명                  |
|--------|----------|------------|-----------------------|
| userId | Y        | Long       | 일정을 작성할 유저 ID |
| title  | Y        | String     | 일정 제목             |
| detail | Y        | String     | 일정 내용             |

# 3. 응답(Response) 

---

## a. Response header

| 이름         | 데이터타입 | 설명                                |
|--------------|------------|-------------------------------------|
| Content-Type | String     | 응답 데이터 형식 (application/json) |

## b. Response body

**성공 응답: 201 Created**

```json
{
  "id": 1,
  "name": "홍길동",
  "title": "Spring 공부",
  "detail": "일정 관리 앱 과제 진행",
  "createdAt": "2026-10-21T09:00:00",
  "updatedAt": "2026-10-21T09:00:00"
}
```

## c. Response Body 명세

| 이름      | 데이터 타입 | 설명                          |
|-----------|-------------|-------------------------------|
| id        | Long        | 일정 식별자                   |
| name      | String      | 작성자 이름                   |
| title     | String      | 일정 제목                     |
| detail    | String      | 일정 내용                     |
| createdAt | String      | 작성일(yyyy-MM-dd'T'HH:mm:ss) |
| updatedAt | String      | 수정일(yyyy-MM-dd'T'HH:mm:ss) |


# 에러 응답
**잘못된 요청: 400 Bad Request**

- userId거 누락되거나 null인 경우입니다. 
- 제목 또는 내용이 누락되거나 공백만 있는 경우입니다.

**존재하지 않는 유저: 404 Not Found**

전달받은 userId에 해당하는 유저가 존재하지 않는 경우입니다.

응답
```text
없는 유저 입니다.
```




