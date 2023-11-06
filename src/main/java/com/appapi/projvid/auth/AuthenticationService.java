package com.appapi.projvid.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.appapi.projvid.Services.JwtService;
import com.appapi.projvid.Services.RecoverTokenService;
import com.appapi.projvid.dtos.authDto.AuthenticationRequestDTO;
import com.appapi.projvid.dtos.authDto.AuthenticationResponseDTO;
import com.appapi.projvid.dtos.authDto.RegisterRequestDTO;
import com.appapi.projvid.entity.user.UserAccess;
import com.appapi.projvid.repository.AccessRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Service
public class AuthenticationService {
  @Autowired
  AccessRepository repository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtService jwtService;

  @Autowired
  AuthenticationManager authenticationManager;

  public AuthenticationResponseDTO register(RegisterRequestDTO request) {
    var user = UserAccess.builder()
        .username(request.username())
        .password(passwordEncoder.encode(request.password()))
        .isLogged(false)
        .active(true)
        .role(request.role())
        .build();
    repository.save(user);
    var jwtToken = jwtService.generateToken(user);

    return new AuthenticationResponseDTO(jwtToken);
  }

  public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.username(),
            request.password()));

    var user = repository.findByUsername(request.username())
        .orElseThrow();

    repository.updateIsLoggedById(user.getId(), true);
    var jwtToken = jwtService.generateToken(user);

    return new AuthenticationResponseDTO(jwtToken);  
  }

  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

    final String refreshToken = new RecoverTokenService().recoverToken(request);
    if (refreshToken == null) {
      return;
    }

    final String username = jwtService.extractUsername(refreshToken);

    if (username != null) {
      var user = this.repository.findByUsername(username).orElseThrow();

      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);

        var authResponse = new AuthenticationResponseDTO(accessToken);
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}