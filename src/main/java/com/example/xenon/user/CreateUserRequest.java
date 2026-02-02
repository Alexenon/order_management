package com.example.xenon.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequest {

    private final String username;
    private final String email;
    private final String password;

}
