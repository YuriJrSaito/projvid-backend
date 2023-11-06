package com.appapi.projvid.dtos.authDto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDTO (@NotBlank String username, @NotBlank String password){
    
}
