- **domain:** user
- **URL:** `http://localhost:8080/users/{userId}`
- **method:** `GET`
- **인증/인가:** 없음

# 1. 설명

---

유저 ID를 이용하여 특정 유저의 상세 정보를 조회하는 API입니다.
- 조회할 유저 ID는 URL에 입력합니다.
- 존재하지 않는 유저는 조회할 수 없습니다.

# 2. 요청(Request)

---

## a. Parameter & Querystring

```http 
GET http://localhost:8080/users/1
```

| 이름   | 데이터타입 | 필수 | 설명               |
|--------|------------|------|--------------------|
| userId | Long       | Y    | 조회할 유저 식별자 |

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
| email     | String      | 유저 이메일                   |
| createdAt | String      | 작성일(yyyy-MM-dd'T'HH:mm:ss) |
| updatedAt | String      | 수정일(yyyy-MM-dd'T'HH:mm:ss) |


## 에러 응답
**유저를 찾을 수 없음: 404 Not Found**

요청한 유저가 존재하지 않는 경우입니다.

```text
없는 유저 입니다.
```
