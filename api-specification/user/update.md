- **domain:** user
- **URL:** `http://localhost:8080/users/{userId}`
- **method:** `PATCH`
- **인증/인가:** 없음

# 1. 설명

---

기존 유저는 이메일만 수정하는 API입니다.
- 수정할 유저 ID는 URL에 입력합니다.
- 이메일만 수정할 수 있으며, 유저명은 변결할 수 없습니다.
- 이메일은 필수 입력값입니다.
- 이메일은 수정하면 수정일은 자동으로 갱신됩니다.

# 2. 요청(Request)

---

## a. Parameter & Querystring

```http 
PATCH http://localhost:8080/users/1
```

| 이름   | 데이터타입 | 필수여부 | 설명               |
|--------|------------|----------|--------------------|
| userId | Long       | Y        | 수정할 유저 식별자 |


## b. Request headers

| 이름         | 데이터타입 | 설명                                |
|--------------|------------|-------------------------------------|
| Content-Type | String     | 요청 데이터 형식 (application/json) |


## c. Request Body

```json
{
  "email": "123@naver.com"
}
```

## d. Request Body 명세

| 이름  | 필수여부 | 데이터타입 | 설명               |
|-------|----------|------------|--------------------|
| email | Y        | String     | 수정할 유저 이메일 |


## 3. 응답(Response)

---

## a. Response header

| 이름         | 데이터타입 | 설명                                |
|--------------|------------|-------------------------------------|
| Content-Type | String     | 응답 데이터 형식 (application/json) |

## b. Response body

**성공 응답: 200 OK**

기존 이메일이 `sparta@naver.com`일 때 변경 결과 입니다.

변환 후

```json
{
  "id": 1,
  "name": "홍길동",
  "email": "123@naver.com",
  "createdAt": "2026-10-21T09:00:00",
  "updatedAt": "2026-10-21T10:00:00"
}
```

## c. Response Body 명세

| 이름      | 데이터 타입 | 설명                          |
|-----------|-------------|-------------------------------|
| id        | Long        | 유저 식별자                   |
| name      | String      | 유저명                        |
| email     | String      | 유저 이메일                   |
| createdAt | String      | 작성일(yyyy-MM-dd'T'HH:mm:ss) |
| updatedAt | String      | 수정일(yyyy-MM-dd'T'HH:mm:ss) |


## 에러 응답

**자원 없음: 404 Not Found**

수정할 유저가 존재하지 않는 경우입니다.

```text
없는 유저 입니다.
```

**잘못된 요청: 400 Bad Request**

이메일이 누락되거나 빈 문자열 또는 공백만 있는 경우입니다.
