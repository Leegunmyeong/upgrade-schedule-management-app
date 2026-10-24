- **domain:** schedule
- **URL:** `http://localhost:8080/schedules`
- **method:** `POST`

# 1. 설명

---

새로운 일정을 생성하는 API입니다.
- 작성자 이름, 제목, 내용은 필수 입력값입니다.
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
  "name": "홍길동",
  "title": "Spring 공부",
  "detail": "일정 관리 앱 과제 진행"
}
```

## d. Request Body 명세

| 이름   | 필수여부 | 데이터타입 | 설명        |
|--------|----------|------------|-------------|
| name   | Y        | String     | 작성자 이름 |
| title  | Y        | String     | 일정 제목   |
| detail | Y        | String     | 일정 내용   |

## 3. 응답(Response) 

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


## 에러 응답
**잘못된 요청: 400 Bad Request**

필수 입력값이 누락되거나 비어 있는 경우입니다


