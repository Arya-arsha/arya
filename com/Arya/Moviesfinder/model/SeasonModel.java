package com.Arya.Moviesfinder.model;

import java.io.InputStream;
import java.sql.Date;

public class SeasonModel {
	private int id;
	private int season=0;
	private int episodeNumber;
	private Date releaseDate=null;
	private InputStream seasonImage=null;
	
	public SeasonModel() {
		super();
	}
	
	public SeasonModel(int season, int episodeNumber, Date releaseDate, InputStream seasonImage) {
		this.season = season;
		this.episodeNumber = episodeNumber;
		this.releaseDate = releaseDate;
		this.seasonImage = seasonImage;
	}

	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public int getEpisodeNumber() {
		return episodeNumber;
	}
	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public InputStream getSeasonImage() {
		return seasonImage;
	}
	public void setSeasonImage(InputStream seasonImage) {
		this.seasonImage = seasonImage;
	}
	public boolean isEmpty() {
		if (season==0) {
			return true;
		}
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
