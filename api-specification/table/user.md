# User 테이블 명세서

**테이블 관계: User(1) : Schedule(N)

| 컬럼명     | 데이터 타입  | 제약조건           | 설명             |
|------------|--------------|--------------------|------------------|
| id         | BIGINT       | PK, AUTO_INCREMENT | 유저 고유 식별자 |
| name       | VARCHAR(255) | NOT NULL           | 유저명           |
| email      | VARCHAR(255) | NOT NULL, UNIQUE   | 유저 이메일      |
| pw         | VARCHAR(255) | NOT NULL           | 비밀번호         |
| created_at | DATETIME(6)  | NOT NULL           | 유저 생성일      |
| updated_at | DATETIME(6)  | NOT NULL           | 유저 수정일      |

DATETIME(6) -> 마이크로초 단위까지 저장
Response Dto에서 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")로 초까지만 출력 지정