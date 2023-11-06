package com.appapi.projvid.dtos.authDto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationResponseDTO(@NotBlank String access_token){
    
}
