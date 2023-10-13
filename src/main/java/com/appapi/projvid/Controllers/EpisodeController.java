package com.appapi.projvid.Controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appapi.projvid.dtos.EpisodeDTO;
import com.appapi.projvid.entity.Episode;
import com.appapi.projvid.repository.EpisodeRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"http://10.0.0.148:3000", "http://localhost:3000"})
@RestController
public class EpisodeController {
	@Autowired
	private EpisodeRepository episodeRepository;
	
	@PostMapping("/episode")
	public ResponseEntity<Object> saveEpisode(@RequestBody @Valid EpisodeDTO episodeDto){
		var episodeModel = new Episode();
		BeanUtils.copyProperties(episodeDto, episodeModel);
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(episodeRepository.save(episodeModel));
		}
		catch (Exception e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	@GetMapping("/episodes/{animeId}/{seasons}")
	public ResponseEntity<Object> getAllEpisodes(@PathVariable UUID animeId, @PathVariable Integer seasons)
	{
		List<List<Episode>> episodes = new ArrayList<List<Episode>>();
		
		for(int i=0; i<seasons; i++) {
			List<Episode> season = episodeRepository.findByAnime_idAndSeason(animeId, i+1);
			season.sort(new Compare());
			episodes.add(season);
		}
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(episodes);
		}
		catch (Exception e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	class Compare implements Comparator<Episode>{
		@Override
		public int compare(Episode a, Episode b) {
			return a.getEpisode() - b.getEpisode();
		}
	}
}
