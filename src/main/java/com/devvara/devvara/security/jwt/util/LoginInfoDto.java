package com.devvara.devvara.security.jwt.util;

import lombok.Data;

@Data
public class LoginInfoDto {
    private Long userId;
    private String email;
    private String name;
}