package com.devvara.devvara.api;

import com.devvara.devvara.security.jwt.util.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/user/")
public class UserApiController {

    private final JwtTokenizer jwtTokenizer;
}
