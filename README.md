# umc-10th-springboot-Kenzi

## 기술 스택

| 분류 | 기술 |
|------|------|
| Language | Java 25 |
| Framework | Spring Boot 4.0.5 |
| ORM | Spring Data JPA / Hibernate |
| Database | MySQL 8.0 |
| API Docs | SpringDoc OpenAPI 3.0.2 (Swagger UI) |
| Build | Gradle |
| Infra | Docker |
| Etc | Lombok |

---

## 도메인 구조

```
src/main/java/com/example/umc10th/
│
├── common/
│   ├── advice/
│   │   ├── GlobalResponseAdvice.java   # 전체 응답 통일 (ResponseBodyAdvice)
│   │   └── GlobalExceptionHandler.java # 전역 예외 처리
│   └── response/
│       ├── ApiResponse.java            # 통합 응답 래퍼
│       ├── ApiError.java               # 에러 정보
│       └── ApiMeta.java                # 타임스탬프
│
├── auth/                               # 인증 도메인
│   ├── controller/                     # GET /auth
│   ├── service/
│   ├── repository/
│   └── entity/                        # RefreshToken (RefreshToken 테이블)
│
├── users/                              # 유저 도메인
│   ├── controller/                     # GET /users
│   ├── service/
│   ├── repository/
│   ├── entity/                        # User (User 테이블)
│   └── enums/                         # UserRole (CEO, ADMIN, USER)
│
├── store/                              # 가게 도메인
│   ├── controller/                     # GET /store
│   ├── service/
│   ├── repository/
│   └── entity/                        # Store (Store 테이블)
│
├── missions/                           # 미션 도메인
│   ├── controller/                     # GET /missions
│   ├── service/
│   ├── repository/
│   └── entity/                        # Mission (Mission 테이블)
│
├── reference/                          # 레퍼런스 도메인
│   ├── controller/                     # GET /reference
│   ├── service/
│   ├── repository/
│   └── entity/                        # Address (Address 테이블)
│
└── health/                             # 헬스 체크
    ├── HealthController.java           # GET /health/liveness, GET /health/readiness
    ├── HealthService.java
    └── HealthResponse.java
```

---

## 통합 응답 형식

```json
{
  "success": true,
  "data": { },
  "error": {
    "error-code": null,
    "error-message": null
  },
  "meta": {
    "time-stamp": "2026-04-03T00:00:00"
  }
}
```

---

## 실행 방법

```bash
# DB 실행
docker-compose up -d

# 서버 실행
./gradlew bootRun
```

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
