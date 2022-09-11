package com.alc.gestock.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResquest {

    private String login;
    private String password;
}
