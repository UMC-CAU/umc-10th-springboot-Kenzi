package com.example.umc10th.health;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;

@Service
@RequiredArgsConstructor
public class HealthService {

    private final DataSource dataSource;

    public boolean isDatabaseReachable() {
        try (Connection connection = dataSource.getConnection()) {
            return connection.isValid(3);
        } catch (Exception e) {
            return false;
        }
    }
}
