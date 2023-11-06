package com.appapi.projvid.dtos.authDto;

import com.appapi.projvid.entity.user.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public  record RegisterRequestDTO(@NotBlank String username, @NotBlank String password, @NotNull UserRole role){
    
}
