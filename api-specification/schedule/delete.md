- **domain:** schedule
- **URL:** `http://localhost:8080/schedules/{scheduleId}`
- **method:** `DELETE`

# 1. 설명

---

일정 ID를 이용하여 특정 일정을 삭제하는 API입니다.

- 삭제할 일정 ID는 URL에 입력합니다.
- 삭제에 성공하면 응답 본문 없이 반환합니다.

# 2. 요청(Request)

---

## a. Parameter & Querystring

```http 
DELETE http://localhost:8080/schedules/1
```

| 이름       | 데이터타입 | 필수여부 | 설명               |
|------------|------------|----------|--------------------|
| scheduleId | Long       | Y        | 삭제할 일정 식별자 |


## b. Request headers

- 없음

## c. Request Body

- 없음

## d. Request Body 명세

- 없음

## 3. 응답(Response)

---

## a. Response header

- 없음

## b. Response body

**성공 응답: 204 No Content**
- 응답 본문 없음


## c. Response Body 명세

- 없음

## 에러 응답

**자원 없음: 404 Not Found**

삭제할 일정이 존재하지 않는 경우입니다.

```json
{
  "message": "없는 일정 입니다."
}
```
