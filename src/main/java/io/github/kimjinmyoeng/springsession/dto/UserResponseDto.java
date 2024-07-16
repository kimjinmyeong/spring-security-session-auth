package io.github.kimjinmyoeng.springsession.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    @Schema(type = "long")
    private Long id;

    @Schema(type = "string")
    private String username;

    @Schema(type = "string")
    private String password;
}
