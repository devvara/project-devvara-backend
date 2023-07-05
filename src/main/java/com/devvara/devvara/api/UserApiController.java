package com.devvara.devvara.api;

import com.devvara.devvara.domain.RefreshToken;
import com.devvara.devvara.security.jwt.util.JwtTokenizer;
import com.devvara.devvara.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/user/")
public class UserApiController {

    private final JwtTokenizer jwtTokenizer;

    private final UserService userService;

    private final RefreshToken refreshToken;

    private final PasswordEncoder passwordEncoder;

}
