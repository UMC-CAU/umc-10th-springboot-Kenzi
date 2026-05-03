package com.example.umc10th.global.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiMeta {

    @JsonProperty("time-stamp")
    private final LocalDateTime timeStamp;

    public ApiMeta() {
        this.timeStamp = LocalDateTime.now();
    }
}
