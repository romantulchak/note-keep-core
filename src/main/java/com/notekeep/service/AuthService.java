package com.notekeep.service;

import com.notekeep.dto.JwtDTO;
import com.notekeep.payload.request.auth.LoginRequest;
import com.notekeep.payload.request.auth.RegistrationRequest;


public interface AuthService {

    JwtDTO signIn(LoginRequest loginRequest);

    void signUp(RegistrationRequest registrationRequest);
}
