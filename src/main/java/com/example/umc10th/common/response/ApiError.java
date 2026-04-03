package com.example.umc10th.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ApiError {

    @JsonProperty("error-code")
    private final String errorCode;

    @JsonProperty("error-message")
    private final String errorMessage;

    public ApiError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static ApiError empty() {
        return new ApiError(null, null);
    }
}
