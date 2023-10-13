package com.appapi.projvid.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "episodes")
public class Episode implements Serializable{

	@Serial
	private static final long serialVersionUID = 2765821260250568277L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String img;
	@Column(nullable = false)
	private String link;
	@Column(nullable = false)
	private int season;
	@Column(nullable = false)
	private int episode;
	
	@ManyToOne
	@JoinColumn(name = "anime_id", referencedColumnName = "id")
	private Anime anime;
	
	public Episode() {
		
	}
	
	public Episode(String title, String img, String link, int season, Anime anime, int episode) {
		this.title = title;
		this.img = img;
		this.link = link;
		this.season = season;
		this.episode = episode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public Anime getAnime() {
		return anime;
	}

	public void setAnime(Anime anime) {
		this.anime = anime;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}
}
