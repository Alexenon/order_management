package com.example.xenon.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@SuppressWarnings("ClassCanBeRecord")
public class CreateUserRequest {

    private final String username;
    private final String email;
    private final String password;

    @Builder
    public CreateUserRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
