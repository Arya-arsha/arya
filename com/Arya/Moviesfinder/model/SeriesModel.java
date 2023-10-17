package com.Arya.Moviesfinder.model;



public class SeriesModel extends Model {
	
	private String seriesId=null;
	private String seriesName=null;
	private SeasonModel season=null;
	
	public String getSeriesname() {
		return seriesName;
	}
	public void setSeriesname(String seriesname) {
		this.seriesName = seriesname;
	}
	@Override
	public String toString() {
		return seriesName;
	}
	public String getSeriesId() {
		return seriesId;
	}
	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}
	public SeasonModel getSeason() {
		return season;
	}
	public void setSeason(SeasonModel season) {
		this.season = season;
	}
	public boolean isEmpty() {
		if (seriesName==null && seriesName.equals("")) {
			return true;
		}
		return false;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (seriesId.equals(((SeriesModel)obj).seriesId) 
				&& season.getSeason()==((SeriesModel)obj).season.getSeason()) {
			return true;
		}
		return false;
	}
	
	

}
