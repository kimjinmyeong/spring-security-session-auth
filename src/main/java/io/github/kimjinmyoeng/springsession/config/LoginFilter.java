package io.github.kimjinmyoeng.springsession.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kimjinmyoeng.springsession.dto.UserRequestDto;
import io.github.kimjinmyoeng.springsession.dto.UserResponseDto;
import io.github.kimjinmyoeng.springsession.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Custom authentication filter that handles the authentication of users based on their credentials.
 */
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final ObjectMapper objectMapper;

    /**
     * Attempts to authenticate the user based on the credentials provided in the HTTP request.
     */
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            // request.getInputStream() is a method call that retrieves the input stream of the HTTP request body.
            UserRequestDto userRequestDto = new ObjectMapper().readValue(request.getInputStream(), UserRequestDto.class);
            String username = userRequestDto.getUsername();
            String password = userRequestDto.getPassword();

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

            /*
             * The authenticate method performs the following detailed steps:
             * Token Creation: Create an Authentication token with the user's credentials.
             * Provider Loop: Iterate over available AuthenticationProviders.
             * Provider Support Check: Find a provider that supports the Authentication token.
             * User Details Retrieval: Use UserDetailsService to load user details.
             * Password Validation: Validate the password using PasswordEncoder.
             * Authenticated Token Creation: Create a new token with authenticated user details.
             * Set Security Context: Store the authenticated token in the SecurityContextHolder.
             */
            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles successful authentication by setting the authentication in the security context
     * and returning a custom JSON response with user details.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException {
        SecurityContextHolder.getContext().setAuthentication(authentication);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=UTF-8");

        User user = (User) authentication.getPrincipal();
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setPassword(user.getPassword());

        String jsonResponse = objectMapper.writeValueAsString(userResponseDto);
        PrintWriter writer = response.getWriter();
        writer.write(jsonResponse);
        writer.flush();
    }

    /**
     * Handles unsuccessful authentication by setting the response status to 401 (Unauthorized)
     * and returning a custom JSON error response with the authentication failure message.
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=UTF-8");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, failed.getMessage());
    }

}
