- **domain:** schedule
- **URL:** `http://localhost:8080/schedules/{scheduleId}`
- **method:** `PATCH`

# 1. 설명

---

기존 일정의 제목 또는 내용을 수정하는 API입니다.
- 수정할 일정 ID는 URL에 입력합니다.
- 제목과 내용 중 하나 이상 입력해야 합니다.
- 입력하지 않은 항목은 기존 값을 유지합니다.
- 수정하면 수정일은 자동으로 변경 됩니다.

# 2. 요청(Request)

---

## a. Parameter & Querystring

```http 
PATCH http://localhost:8080/schedules/1
```

| 이름       | 데이터타입 | 필수여부 | 설명               |
|------------|------------|----------|--------------------|
| scheduleId | Long       | Y        | 수정할 일정 식별자 |


## b. Request headers

| 이름         | 데이터타입 | 설명                                |
|--------------|------------|-------------------------------------|
| Content-Type | String     | 요청 데이터 형식 (application/json) |


## c. Request Body

```json
{
  "title": "Spring 공부",
  "detail": "일정 수정 API 구현하기"
}
```

## d. Request Body 명세

| 이름   | 필수여부 | 데이터타입 | 설명             |
|--------|----------|------------|------------------|
| title  | N        | String     | 수정할 일정 제목 |
| detail | N        | String     | 수정할 일정 내용 |

**title과 detail 중 최소 하나는 입력해야합니다**
**그 외에 데이터는 수정할 수 없습니다**

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
  "detail": "일정 수정 API 구현하기",
  "createdAt": "2026-10-21T09:00:00",
  "updatedAt": "2026-10-21T09:00:00"
}
```
변환 후

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

| 이름      | 데이터 타입 | 설명                        |
|-----------|-------------|-----------------------------|
| id        | Long        | 일정 식별자                 |
| name      | String      | 작성자 이름                 |
| title     | String      | 일정 제목                   |
| detail    | String      | 일정 내용                   |
| createdAt | String      | 작성일(yyyy-mm-ddThh:mm:ss) |
| updatedAt | String      | 수정일(yyyy-mm-ddThh:mm:ss) |


## 에러 응답

**자원 없음: 404 Not Found**

수정할 일정이 존재하지 않는 경우입니다.

```json
{
  "message": "없는 일정 입니다."
}
```
