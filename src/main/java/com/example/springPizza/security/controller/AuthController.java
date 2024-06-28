package com.example.springPizza.security.controller;

import com.example.springPizza.security.JwtAuthenticationResponse;
import com.example.springPizza.security.dto.JwtResponseDTO;
import com.example.springPizza.security.dto.RefreshTokenRequestDTO;
import com.example.springPizza.security.dto.SignInRequest;
import com.example.springPizza.security.dto.SignUpRequest;
import com.example.springPizza.security.model.RefreshToken;
import com.example.springPizza.security.services.AuthenticationService;
import com.example.springPizza.security.services.JwtService;
import com.example.springPizza.security.services.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody SignInRequest authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            return JwtResponseDTO.builder()
                    .accessToken(authenticationService.signIn(authRequestDTO))
                    .token(refreshToken.getToken())
                    .build();

        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }
    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtResponseDTO signUp(@RequestBody @Valid SignUpRequest request) {
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getUsername());
        return JwtResponseDTO.builder()
                .accessToken(authenticationService.signUp(request))
                .token(refreshToken.getToken())
                .build();
    }
//    @Operation(summary = "Авторизация пользователя")
//    @PostMapping("/sign-in")
//    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
//        return authenticationService.signIn(request);
//    }
    @PostMapping("/refreshToken")
    public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo);
                    return JwtResponseDTO.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }
}