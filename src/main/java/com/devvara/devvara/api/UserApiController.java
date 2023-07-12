package com.devvara.devvara.api;

import com.devvara.devvara.domain.RefreshToken;
import com.devvara.devvara.domain.User;
import com.devvara.devvara.dto.UserSignupDto;
import com.devvara.devvara.dto.UserSignupResponseDto;
import com.devvara.devvara.security.jwt.util.JwtTokenizer;
import com.devvara.devvara.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1")
public class UserApiController {

    private final JwtTokenizer jwtTokenizer;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/user/signup")
    public ResponseEntity signup(@RequestBody @Valid UserSignupDto userSignupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(userSignupDto.getName());
        user.setEmail(userSignupDto.getEmail());
        user.setPassword(passwordEncoder.encode(userSignupDto.getPassword()));

        User saveUser = userService.addUser(user);

        UserSignupResponseDto userSignupResponseDto = new UserSignupResponseDto();
        userSignupResponseDto.setUserId(saveUser.getId());
        userSignupResponseDto.setName(saveUser.getName());
        userSignupResponseDto.setEmail(saveUser.getEmail());
        userSignupResponseDto.setRegdate(saveUser.getCreatedAt());

        return new ResponseEntity(userSignupResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid )

}
