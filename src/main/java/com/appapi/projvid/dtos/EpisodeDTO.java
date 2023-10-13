package com.appapi.projvid.dtos;

import com.appapi.projvid.entity.Anime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EpisodeDTO(@NotBlank @NotNull String title, @NotBlank @NotNull String img,
		@NotBlank @NotNull String link, @NotNull int season, @NotNull Anime anime, @NotNull int episode){

}
