package com.notekeep.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @Email(message = "user.email.incorrect")
    @Size(min = 4, max = 85)
    private String email;

    @Size(min = 4, max = 60)
    private String password;
}
