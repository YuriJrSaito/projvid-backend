package com.appapi.projvid.Services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.appapi.projvid.repository.AccessRepository;

@Service
public class LogoutService implements LogoutHandler {

    @Autowired
    AccessRepository repository;

    @Autowired
    JwtService jwtService;

    @Autowired
	RecoverTokenService recoverToken;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        
        final String jwt = recoverToken.recoverToken(request);
		if (jwt != null) {
            final String username = jwtService.extractUsername(jwt);
            var user = repository.findByUsername(username).orElseThrow();
            repository.updateIsLoggedById(user.getId(), false);
            SecurityContextHolder.clearContext();
		}
    }
}