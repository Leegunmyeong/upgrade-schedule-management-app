# Schedule 테이블 명세서

**테이블 관계: User(1) : Schedule(N)

| 컬럼명     | 데이터 타입  | 제약조건           | 설명             |
|------------|--------------|--------------------|------------------|
| id         | BIGINT       | PK, AUTO_INCREMENT | 일정 고유 식별자 |
| user_id    | BIGINT       | FK, NOT NULL       | 작성 유저 ID     |
| title      | VARCHAR(255) | NOT NULL           | 일정 제목        |
| detail     | VARCHAR(255) | NOT NULL           | 일정 내용        |
| created_at | DATETIME(6)  | NOT NULL           | 일정 생성일      |
| updated_at | DATETIME(6)  | NOT NULL           | 일정 수정일      |

DATETIME(6) -> 마이크로초 단위까지 저장
Response Dto에서 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")로 초까지만 출력 지정