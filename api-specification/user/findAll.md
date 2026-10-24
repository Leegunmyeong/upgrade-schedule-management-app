- **domain:** user
- **URL:** `http://localhost:8080/users`
- **method:** `GET`
- **인증/인가:** 없음

# 1. 설명

---

등록된 모든 유저 목록을 조회하는 API입니다.
- 유저는 수정일 기준 내림차순으로 조회됩니다.
- 등록된 유저가 없는 경우 빈 목록을 반환합니다.

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
  "userList": [
    {
      "id": 2,
      "name": "김철수",
      "email": "asd12@naver.com",
      "createdAt": "2026-10-22T16:00:00",
      "updatedAt": "2026-10-22T16:00:00"
    },
    {
      "id": 1,
      "name": "홍길동",
      "email": "sparta@naver.com",
      "createdAt": "2026-10-22T15:00:00",
      "updatedAt": "2026-10-22T15:00:00"
    }
  ]
}
```

## c. Response Body 명세

### 최상위 응답

| 이름     | 데이터 타입 | 설명             |
|----------|-------------|------------------|
| userList | Array       | 조회한 유저 목록 |

### userList 내부 데이터

| 이름      | 데이터 타입 | 설명                          |
|-----------|-------------|-------------------------------|
| id        | Long        | 유저 식별자                   |
| name      | String      | 유저명                        |
| email     | String      | 유저 이메일                   |
| createdAt | String      | 작성일(yyyy-MM-dd'T'HH:mm:ss) |
| updatedAt | String      | 수정일(yyyy-MM-dd'T'HH:mm:ss) |

**조회 결과가 없는 경우: 200 OK**

```json
{
  "userList": []
}
```
