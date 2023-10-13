package com.appapi.projvid.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnimeDTO(@NotBlank @NotNull String title, @NotBlank @NotNull String img,
		@NotBlank @NotNull String description, @NotNull String link,
		@NotNull int seasons){

}
