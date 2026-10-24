- **domain:** user
- **URL:** `http://localhost:8080/users/{userId}`
- **method:** `DELETE`
- **인증/인가:** 없음

# 1. 설명

---

유저 ID를 이용하여 특정 유저를 삭제하는 API입니다.

- 삭제할 유저 ID는 URL에 입력합니다.
- 삭제에 성공하면 응답 본문 없이 반환합니다.

# 2. 요청(Request)

---

## a. Parameter & Querystring

```http 
DELETE http://localhost:8080/users/1
```

| 이름   | 데이터타입 | 필수여부 | 설명               |
|--------|------------|----------|--------------------|
| userId | Long       | Y        | 삭제할 유저 식별자 |


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

**유저를 찾을 수 없음: 404 Not Found**

삭제할 유저가 존재하지 않는 경우입니다.

```text
없는 유저 입니다.
```
