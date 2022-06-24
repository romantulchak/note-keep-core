package com.notekeep.payload.request.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {

    @Email(message = "user.email.incorrect")
    @Size(min = 4, max = 85)
    private String email;

    @Size(min = 4, max = 60)
    private String password;

    @Size(max = 25)
    private String firstName;

    @Size(max = 25)
    private String lastName;

}
