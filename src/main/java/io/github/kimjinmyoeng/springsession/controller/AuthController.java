package io.github.kimjinmyoeng.springsession.controller;

import io.github.kimjinmyoeng.springsession.dto.UserRequestDto;
import io.github.kimjinmyoeng.springsession.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserRequestDto user) {
        userService.register(user);
        return "User registered successfully";
    }

    /** login and logout method is intentionally left empty because the login and logout functionality
     * is handled by the filter chain configuration in Spring Security.
     */
    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto userRequestDto) {
        return "User logged in successfully";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        return "User logged out successfully";
    }

    @GetMapping("/session")
    public String session(HttpSession session) {
        long creationTime = session.getCreationTime();
        Date creationDate = new Date(creationTime);

        // Format the date to a readable string
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(creationDate);
    }

}
