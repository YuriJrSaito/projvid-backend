package com.appapi.projvid.Services;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class RecoverTokenService {

    public String recoverToken(HttpServletRequest request) {
        var authReader = request.getHeader("Authorization");
        if (authReader == null)
            return null;
        return authReader.replace("Bearer", "");
    }
}
