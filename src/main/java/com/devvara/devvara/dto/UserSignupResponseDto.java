package com.devvara.devvara.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserSignupResponseDto {
    private Long userId;
    private String email;
    private String name;
    private LocalDateTime regdate;
}
