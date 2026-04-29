package com.example.umc10th.health;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health", description = "헬스 체크 API")
@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class HealthController {

    private final HealthService healthService;

    /**
     * Liveness Check: 애플리케이션 프로세스가 살아있는지 확인.
     * K8s가 프로세스 재시작 여부를 판단하는 데 사용.
     */
    @Operation(summary = "Liveness Check", description = "애플리케이션이 실행 중인지 확인합니다.")
    @GetMapping("/liveness")
    public ResponseEntity<HealthResponse> liveness() {
        return ResponseEntity.ok(HealthResponse.up("Application is alive"));
    }

    /**
     * Readiness Check: 애플리케이션이 트래픽을 받을 준비가 되었는지 확인.
     * DB 연결 가능 여부를 포함. 실패 시 503을 반환해 로드밸런서가 라우팅을 제외하도록 함.
     */
    @Operation(summary = "Readiness Check", description = "애플리케이션이 요청을 처리할 준비가 되었는지 확인합니다. (DB 연결 포함)")
    @GetMapping("/readiness")
    public ResponseEntity<HealthResponse> readiness() {
        if (healthService.isDatabaseReachable()) {
            return ResponseEntity.ok(HealthResponse.up("Application is ready"));
        }
        return ResponseEntity
                .status(503)
                .body(HealthResponse.down("Database is not reachable"));
    }
}
