package com.Arya.Moviesfinder.model;


import java.sql.Date;

public class MovieModel extends Model {
	
	private String movieName=null;
	private Date ottReleaseDate=null;
	private Date  ThreatreReleaseDate=null;
	private String releaseType;

	

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	

	public Date getOttReleaseDate() {
		return ottReleaseDate;
	}

	public void setOttReleaseDate(Date ottReleaseDate) {
		this.ottReleaseDate = ottReleaseDate;
	}

	public Date getThreatreReleaseDate() {
		return ThreatreReleaseDate;
	}

	public void setThreatreReleaseDate(Date threatreReleaseDate) {
		ThreatreReleaseDate = threatreReleaseDate;
	}

	public String getReleaseType() {
		return releaseType;
	}

	public void setReleaseType(String releaseType) {
		this.releaseType = releaseType;
	}
	public boolean isEmpty() {
		if (movieName==null && movieName.equals("")) {
			return true;	
		}
		return false;
	}
	@Override
	public String toString() {
		return movieName ;
	}

	





	

	
	
	

}
