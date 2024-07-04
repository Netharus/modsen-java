package com.example.springPizza.security.controller;

import com.example.springPizza.security.dto.JwtResponseDto;
import com.example.springPizza.security.dto.RefreshTokenRequestDto;
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
    public JwtResponseDto AuthenticateAndGetToken(@RequestBody SignInRequest authRequestDTO){
        Authentication authentication;
        if(authRequestDTO.getUsername()==null){
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));

        }else{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        }
        if(authentication.isAuthenticated()){
            RefreshToken refreshToken;
            if(authRequestDTO.getUsername()==null) {
                refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getEmail());
            }else{
                refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            }
            return JwtResponseDto.builder()
                    .accessToken(authenticationService.signIn(authRequestDTO))
                    .token(refreshToken.getToken())
                    .build();

        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }
    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtResponseDto signUp(@RequestBody @Valid SignUpRequest request) {
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getUsername());
        return JwtResponseDto.builder()
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
    public JwtResponseDto refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDTO){
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo);
                    return JwtResponseDto.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }
}