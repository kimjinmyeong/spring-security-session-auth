package io.github.kimjinmyoeng.springsession.controller;

import io.github.kimjinmyoeng.springsession.dto.UserRequestDto;
import io.github.kimjinmyoeng.springsession.model.User;
import io.github.kimjinmyoeng.springsession.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserRequestDto user) {
        userService.register(user);
        return "User registered successfully";
    }



    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto userRequestDto) {
        return "User logged in successfully";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        return "User logged out successfully";
    }

}
