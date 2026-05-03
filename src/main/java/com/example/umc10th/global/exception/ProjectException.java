package com.example.umc10th.global.exception;

import com.example.umc10th.global.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException {

    public final BaseErrorCode code;

}
