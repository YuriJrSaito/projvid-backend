package com.appapi.projvid.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appapi.projvid.entity.Anime;

public interface AnimeRepository extends JpaRepository<Anime, UUID>{
	
}
