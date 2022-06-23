package com.notekeep.controller;

import com.notekeep.dto.JwtDTO;
import com.notekeep.payload.request.LoginRequest;
import com.notekeep.payload.request.RegistrationRequest;
import com.notekeep.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(value = "*", maxAge = 3600L)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public JwtDTO signIn(@Valid @RequestBody LoginRequest loginRequest){
        return authService.signIn(loginRequest);
    }

    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody RegistrationRequest registrationRequest){
        authService.signUp(registrationRequest);
    }
}
