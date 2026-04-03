package com.example.umc10th.common.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"success", "data", "error", "meta"})
public class ApiResponse<T> {

    private final boolean success;
    private final T data;
    private final ApiError error;
    private final ApiMeta meta;

    private ApiResponse(boolean success, T data, ApiError error) {
        this.success = success;
        this.data = data;
        this.error = error;
        this.meta = new ApiMeta();
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, ApiError.empty());
    }

    public static <T> ApiResponse<T> error(String errorCode, String errorMessage) {
        return new ApiResponse<>(false, null, new ApiError(errorCode, errorMessage));
    }
}
