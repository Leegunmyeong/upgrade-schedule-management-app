- **domain:** user
- **URL:** `http://localhost:8080/users`
- **method:** `POST`
- **인증/인가:** 없음

# 1. 설명

---

새로운 유저를 생성하는 API입니다.
- 유저명과 이메일은 필수 입력값입니다.
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
  "email": "sparta@naver.com"
}
```

## d. Request Body 명세

| 이름  | 필수여부 | 데이터타입 | 설명   |
|-------|----------|------------|--------|
| name  | Y        | String     | 유저명 |
| email | Y        | String     | 이메일 |

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
  "email": "sparta@naver.com",
  "createdAt": "2026-10-21T09:00:00",
  "updatedAt": "2026-10-21T09:00:00"
}
```

## c. Response Body 명세

| 이름      | 데이터 타입 | 설명                          |
|-----------|-------------|-------------------------------|
| id        | Long        | 유저 식별자                   |
| name      | String      | 유저명                        |
| email     | String      | 이메일                        |
| createdAt | String      | 작성일(yyyy-MM-dd'T'HH:mm:ss) |
| updatedAt | String      | 수정일(yyyy-MM-dd'T'HH:mm:ss) |


## 에러 응답
**잘못된 요청: 400 Bad Request**

유저명 또는 이메일이 누락되거나 비어 있는 경우입니다

