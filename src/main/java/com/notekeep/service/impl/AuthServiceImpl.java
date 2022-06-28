package com.notekeep.service.impl;

import com.notekeep.constant.AppConstant;
import com.notekeep.dto.JwtDTO;
import com.notekeep.exception.user.UserAlreadyExistsException;
import com.notekeep.model.User;
import com.notekeep.payload.request.auth.LoginRequest;
import com.notekeep.payload.request.auth.RegistrationRequest;
import com.notekeep.repository.UserRepository;
import com.notekeep.security.JwtUtils;
import com.notekeep.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;


    /**
     * Authenticate user by email and password
     *
     * @param loginRequest to get necessary information to sign in
     * @return {@link JwtDTO} after success sign in
     */
    @Override
    public JwtDTO signIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new JwtDTO(userDetails.getEmail(), jwt);
    }

    /**
     * Creates user with data from client
     * if user exists will be thrown {@link UserAlreadyExistsException}
     *
     * @param registrationRequest to get necessary information to sign up
     */
    @Override
    public void signUp(RegistrationRequest registrationRequest) {
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException();
        }
        User user = new User(registrationRequest.getEmail(), encoder.encode(registrationRequest.getPassword()), AppConstant.USER_ROLE_NAME)
                .setFirstName(registrationRequest.getFirstName())
                .setLastName(registrationRequest.getLastName());
        userRepository.save(user);
    }
}
