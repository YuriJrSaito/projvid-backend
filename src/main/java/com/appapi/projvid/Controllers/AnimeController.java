package com.appapi.projvid.Controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appapi.projvid.dtos.AnimeDTO;
import com.appapi.projvid.entity.Anime;
import com.appapi.projvid.repository.AnimeRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"http://10.0.0.148:3000", "http://localhost:3000"})
@RestController
public class AnimeController {
	@Autowired
	private AnimeRepository animeRepository;
	
	@PostMapping("/anime")
	public ResponseEntity<Object> saveAnime(@RequestBody @Valid AnimeDTO animeDto){
		var animeModel = new Anime();
		BeanUtils.copyProperties(animeDto, animeModel);
		
		try {
			var result = animeRepository.save(animeModel);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		}
		catch (Exception e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	@GetMapping("/animes")
	public ResponseEntity<Object> getAllAnimes(){
		try {
			Pageable pageable = PageRequest.of(0, 10);
			List<Anime> animesList = animeRepository.findAll(pageable).toList();

			if(animesList.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anime database is empty");
			}
			return ResponseEntity.status(HttpStatus.OK).body(animesList);
		}
		catch (Exception e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}
}
