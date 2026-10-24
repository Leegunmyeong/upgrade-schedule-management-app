- **domain:** schedule
- **URL:** `http://localhost:8080/schedules/{scheduleId}`
- **method:** `GET`

# 1. 설명

---

일정 ID를 이용하여 특정 일정의 상세 정보를 조회하는 API입니다.
- 조회할 일정 ID는 URL에 입력합니다.
- 존재하지 않는 일정은 조회할 수 없습니다.

# 2. 요청(Request)

---

## a. Parameter & Querystring

```http 
GET http://localhost:8080/schedules/1
```

| 이름       | 데이터타입 | 필수 | 설명               |
|------------|------------|------|--------------------|
| scheduleId | Long       | Y    | 조회할 일정 식별자 |

## b. Request headers

- 없음


## c. Request Body

- 없음

## d. Request Body 명세

- 없음

## 3. 응답(Response)

---

## a. Response header

| 이름         | 데이터타입 | 설명                                |
|--------------|------------|-------------------------------------|
| Content-Type | String     | 응답 데이터 형식 (application/json) |

## b. Response body

**성공 응답: 200 OK**

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


## 에러 응답
**잘못된 요청: 404 Not Found**

요청한 일정이 존재하지 않는 경우입니다.

```text
없는 일정 입니다.
```
