- **domain:** schedule
- **URL:** `http://localhost:8080/schedules`
- **method:** `GET`

# 1. 설명

---

등록된 모든 일정 목록을 조회하는 API입니다.
- 일정은 수정일 기준 내림차순으로 조회됩니다.
- 등록된 일정이 없는 경우 빈 목록을 반환합니다.

# 2. 요청(Request)

---

## a. Parameter & Querystring

- 없음

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
  "scheduleList": [
    {
      "id": 2,
      "name": "홍길동",
      "title": "코드카타",
      "detail": "프로그래머스 문제 풀기",
      "createdAt": "2026-10-22T16:00:00",
      "updatedAt": "2026-10-22T16:00:00"
    },
    {
      "id": 1,
      "name": "홍길동",
      "title": "Spring 공부",
      "detail": "JPA 공부하기",
      "createdAt": "2026-10-22T15:00:00",
      "updatedAt": "2026-10-22T15:00:00"
    }
  ]
}
```

## c. Response Body 명세

### 최상위 응답

| 이름         | 데이터 타입 | 설명             |
|--------------|-------------|------------------|
| scheduleList | Array       | 조회한 일정 목록 |

### scheduleList 내부 데이터

| 이름      | 데이터 타입 | 설명                        |
|-----------|-------------|-----------------------------|
| id        | Long        | 일정 식별자                 |
| name      | String      | 작성자 이름                 |
| title     | String      | 일정 제목                   |
| detail    | String      | 일정 내용                   |
| createdAt | String      | 작성일(yyyy-mm-ddThh:mm:ss) |
| updatedAt | String      | 수정일(yyyy-mm-ddThh:mm:ss) |

**조회 결과가 없는 경우: 200 OK**

```json
{
  "scheduleList": []
}
```
