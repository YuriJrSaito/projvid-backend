package com.appapi.projvid.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appapi.projvid.entity.Episode;

public interface EpisodeRepository extends JpaRepository<Episode, UUID>{
	
	List<Episode> findByAnime_idAndSeason(UUID id, int season);
	
}
