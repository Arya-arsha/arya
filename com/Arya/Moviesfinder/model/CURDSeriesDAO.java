package com.Arya.Moviesfinder.model;

import java.sql.SQLException;
import java.util.List;

public interface CURDSeriesDAO {
	
	
	 void insertSeries(SeriesModel series)throws SQLException;
	 void insertSeason(SeasonModel season,String seriesId)throws SQLException;
	 void deleteseries(String id)throws SQLException;
	 void updateSeries(SeriesModel series) throws SQLException;
	 void updateSeason(SeasonModel season, String seriesId)throws SQLException;
	 List<Model> retriveSeries(Object patternObject, int type)throws SQLException;
	 void updateChoice(int type,boolean choice,String id)throws SQLException;
	 List<String> retriveSeriesName(String pattern)throws SQLException;
	 
}


