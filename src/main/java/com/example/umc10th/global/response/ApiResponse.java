package com.example.umc10th.global.response;

import com.example.umc10th.global.code.BaseErrorCode;
import com.example.umc10th.global.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"success", "successCode", "data", "error", "meta"})
public class ApiResponse<T> {

    private final boolean success;
    private final BaseSuccessCode successCode;
    private final T data;
    private final BaseErrorCode error;
    private final ApiMeta meta;

    private ApiResponse(boolean success, T data, BaseSuccessCode successCode, BaseErrorCode errorCode, ApiMeta meta) {
        this.success = success;
        this.successCode = success ? successCode : null;
        this.data = data;
        this.error = success ? null : errorCode;
        this.meta = new ApiMeta();
    }

    public static <T> ApiResponse<T> success(BaseSuccessCode successCode, T data) {
        return new ApiResponse<>(true, data, successCode, null , new ApiMeta());
    }

    public static <T> ApiResponse<T> fail(BaseErrorCode errorCode) {
        return new ApiResponse<>(false, null, null ,errorCode , new ApiMeta());
    }
}
