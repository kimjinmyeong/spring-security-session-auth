package io.github.kimjinmyoeng.springsession.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @Schema(type = "string", example = "jmkim")
    private String username;

    @Schema(type = "string", example = "jmkim123")
    private String password;
}
