package com.example.springPizza.security.services;

import com.example.springPizza.models.User;
import com.example.springPizza.repositories.UserRepository;
import com.example.springPizza.security.model.RefreshToken;
import com.example.springPizza.security.repo.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    final int EXP_TIME=600000;

    @Autowired
    UserRepository userRepository;
    //TODO Exception написать
    public RefreshToken createRefreshToken(String username){
        User user=userRepository.findByUsernameOrEmail(username,username).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        Optional<RefreshToken> token = refreshTokenRepository.findByUserInfo(user);
        if(!token.isEmpty()){
            refreshTokenRepository.delete(token.get());
        }
        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(EXP_TIME)) // set expiry of refresh token to 10 minutes - you can configure it application.properties file
                .build();
        return refreshTokenRepository.save(refreshToken);
    }



    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

}
