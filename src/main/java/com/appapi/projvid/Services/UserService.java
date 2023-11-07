package com.appapi.projvid.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.appapi.projvid.dtos.ChangePasswordRequestDTO;
import com.appapi.projvid.entity.user.UserAccess;
import com.appapi.projvid.repository.AccessRepository;

import java.security.Principal;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccessRepository repository;

    public void changePassword(ChangePasswordRequestDTO request, Principal connectedUser) {

        var user = (UserAccess) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }

        if (!request.newPassword().equals(request.confirmationNewPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        repository.save(user);
    }
}