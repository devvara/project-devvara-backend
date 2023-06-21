package com.devvara.devvara.security.jwt.exception;

import lombok.Getter;

public enum JwtExceptionCode {

    INVALID_TOKEN("INVALID_TOKEN", "유효하지 않은 토근"),
    EXPIRED_TOKEN("EXPIRED_TOKEN", "기간이 만료된 토근"),
    UNSUPPORTED_TOKEN("UNSUPPORTED_TOKEN","지원하지 않는 토큰"),
    NOT_FOUND_TOKEN("NOT_FOUND_TOKEN", "토큰 형식의 값을 찾을 수 없음"),
    UNKNOWN_ERROR("UNKNOWN_ERROR", "알려지지 않은 오류");

    @Getter
    private String code;

    @Getter
    private String message;

    JwtExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
