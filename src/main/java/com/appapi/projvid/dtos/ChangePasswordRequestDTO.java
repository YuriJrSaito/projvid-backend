package com.appapi.projvid.dtos;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequestDTO(@NotBlank String currentPassword, @NotBlank String newPassword,
        @NotBlank String confirmationNewPassword) {

}
