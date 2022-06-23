package com.notekeep.service;

import com.notekeep.dto.JwtDTO;
import com.notekeep.payload.request.LoginRequest;
import com.notekeep.payload.request.RegistrationRequest;


public interface AuthService {

    JwtDTO signIn(LoginRequest loginRequest);

    void signUp(RegistrationRequest registrationRequest);
}
