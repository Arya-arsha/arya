package com.Arya.Moviesfinder.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CURDSeries  implements CURDSeriesDAO{
	public static String NO_CONDITION="";
	
	private Connection connect=null;
	
	public CURDSeries() throws SQLException {
		super();
		connect=new DBconnection().dbconnection();
	}
	public void insertSeries(SeriesModel series) throws SQLException {
				String insertSql="insert into series_list (Series_name,Language,interested) values(?,?,?)";
				
				PreparedStatement seriesInsert=connect.prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS);
				seriesInsert.setString(1,series.getSeriesname());
				seriesInsert.setString(2,series.getLanguage());
				seriesInsert.setBoolean(3,series.isInterested());
				seriesInsert.executeUpdate();
				ResultSet idSet= seriesInsert.getGeneratedKeys();
				String id=null;
				while (idSet.next()) {
					id=idSet.getString(1);
				}
				series.setId("S"+id);
				String genreSql="insert into series_genre values(?,?)";
				PreparedStatement genreStatement=connect.prepareStatement(genreSql);
				for (String genre : series.getGenereList()) {
					genreStatement.setString(1, "S"+id);
					genreStatement.setString(2, Model.GENRE.get(genre));
					genreStatement.executeUpdate();
					System.out.println("h");
				}
				connect.createStatement().executeUpdate("insert into series_season (Season_number,Series_ID) values(1,'"
						+ series.getId()+"')");
				connect.close();
		}
		
		

	public void deleteseries(String id) {
		
		
	}

	public void updateSeries(SeriesModel series) throws SQLException {
		// TODO Auto-generated method stub
		String insertSql="update series_list set  Series_name=?,Language=?,interested=? \r\n"
				+ "where Series_ID=?";
		
		PreparedStatement seriesInsert=connect.prepareStatement(insertSql);
		seriesInsert.setString(1,series.getSeriesname());
		seriesInsert.setString(2,series.getLanguage());
		seriesInsert.setBoolean(3,series.isInterested());
		seriesInsert.setString(4, series.getSeriesId());
		seriesInsert.executeUpdate();
		String deleteSql="delete from series_genre where series_id=?";
		PreparedStatement deleteStatement=connect.prepareStatement(deleteSql);
		deleteStatement.setString(1, series.getSeriesId());
		deleteStatement.executeUpdate();
		String genreSql="insert into series_genre values(?,?)";
		PreparedStatement genreStatement=connect.prepareStatement(genreSql);
		for (String genre : series.getGenereList()) {
			genreStatement.setString(1,series.getSeriesId());
			genreStatement.setString(2, Model.GENRE.get(genre));
			genreStatement.executeUpdate();
			
		}
		connect.close();
		
	}

	public List<Model> retriveSeries(Object pattern, int type) throws SQLException {
		// TODO Auto-generated method stub
		String seriesSql=null;
		PreparedStatement nameStatement=null;
		switch (type) {
		case 1:
			seriesSql="select series_list.*,series_season.* from series_list inner join series_season"
					+ " on series_list.Series_ID=series_season.Series_ID where series_list.Series_name like ?";
			nameStatement= connect.prepareStatement(seriesSql);
			nameStatement.setString(1, pattern+"%");
			break;
		case 2:
			seriesSql="select series_list.*,series_season.* from series_list inner join series_season"
					+ " on series_list.Series_ID=series_season.Series_ID where series_season.Release_Date=?";
			nameStatement= connect.prepareStatement(seriesSql);
			nameStatement.setDate(1,(Date)pattern);
			break;
		case 3:
			seriesSql="select series_list.*,series_season.* from series_list inner join series_season"
					+ " on series_list.Series_ID=series_season.Series_ID inner join series_genre on"
					+ " series_genre.series_id=series_list.Series_ID where series_genre.genre_id=?";
			nameStatement= connect.prepareStatement(seriesSql);
			nameStatement.setString(1,Model.GENRE.get(pattern));
			break;
		case 4:
			seriesSql="select series_list.*,series_season.* from series_list inner join series_season"
					+ " on series_list.Series_ID=series_season.Series_ID where series_list."+ pattern+"=true order by rand() limit 7";
			nameStatement= connect.prepareStatement(seriesSql);
			break;
		case 5:
			seriesSql="select series_list.*,series_season.* from series_list inner join series_season"
					+ " on series_list.Series_ID=series_season.Series_ID where series_season.Release_Date"
					+ " between ? and ? order by rand() limit 7";
			nameStatement= connect.prepareStatement(seriesSql);
			nameStatement.setDate(1,Date.valueOf(LocalDate.now().minusDays(7)));
			nameStatement.setDate(2,Date.valueOf(LocalDate.now().plusDays(7)));
		
			break;
		default:
			break;
		}
		
		
		ResultSet nameSet=nameStatement.executeQuery();
		return setSeries(nameSet);
		
	}
	private List<Model> setSeries(ResultSet nameSet) throws SQLException{
		List<Model> nameList=new ArrayList<Model>();
		SeriesModel series=null;
		while (nameSet.next()) {
			series=new SeriesModel();
			series.setSeriesId(nameSet.getString(2));
			series.setSeriesname(nameSet.getString(3));
			series.setLanguage(nameSet.getString(4));
			series.setGenereList(getGenre(nameSet.getString(2)));
			series.setFavourite(nameSet.getBoolean(5));
			series.setWatched(nameSet.getBoolean(6));
			series.setInterested(nameSet.getBoolean(7));
			SeasonModel season=new SeasonModel();
			season.setId(nameSet.getInt(8));
			season.setSeason(nameSet.getInt(9));
			season.setEpisodeNumber(nameSet.getInt(11));
			season.setReleaseDate(nameSet.getDate(12));
			season.setSeasonImage(nameSet.getBinaryStream(13));
			series.setSeason(season);
			series.setImage(season.getSeasonImage());
			nameList.add(series);
		}
		return nameList;
	}

	public List<String> getGenre(String seriesId) throws SQLException{
		List<String> genreList=new ArrayList<String>();
		String genreSql="SELECT genre.genre_name FROM genre inner join series_genre"
				+ " on series_genre.genre_id=genre.genre_id where series_genre.series_id='"
				+seriesId+"'";
		ResultSet genreSet=connect.createStatement().executeQuery(genreSql);
		while(genreSet.next()) {
			genreList.add(genreSet.getString(1));
			
		}
		
		return genreList;
		
	}
	public void insertSeason(SeasonModel season,String seriesId) throws SQLException {
		// TODO Auto-generated method stub
		String insertSql="insert into series_season (Season_number,Series_ID,Episode_number,Release_Date,image) values(?,?,?,?,?)";
		PreparedStatement seasonStatement=connect.prepareStatement(insertSql);
		seasonStatement.setInt(1, season.getSeason());
		seasonStatement.setString(2,seriesId );
		seasonStatement.setInt(3, season.getEpisodeNumber());
		seasonStatement.setDate(4,season.getReleaseDate());
		seasonStatement.setBlob(5, season.getSeasonImage());
		seasonStatement.executeUpdate();
		connect.close();
	}
	public void updateChoice(int type, boolean choice,String id) throws SQLException {
		// TODO Auto-generated method stub
		String choiceUpdate=null;
		PreparedStatement choiceStatement=null;
		switch (type) {
		case 1:
			choiceUpdate="update series_list set favorite=? where Series_ID=?";
			
			break;
		case 2:
			choiceUpdate="update series_list set watched=? where Series_ID=?";
			break;

		default:
			break;
		}
		choiceStatement=connect.prepareStatement(choiceUpdate);
		choiceStatement.setBoolean(1, choice);
		choiceStatement.setString(2, id);
		choiceStatement.executeUpdate();
		connect.close();
		
		
	}
	public void updateSeason(SeasonModel season, String seriesId) throws SQLException {
		// TODO Auto-generated method stub
		String insertSql="update  series_season set Season_number=?,Series_ID=?,Episode_number=?,Release_Date=?,image=? "
				+ "where Season_ID=?";
		PreparedStatement seasonStatement=connect.prepareStatement(insertSql);
		seasonStatement.setInt(1, season.getSeason());
		seasonStatement.setString(2,seriesId );
		seasonStatement.setInt(3, season.getEpisodeNumber());
		seasonStatement.setDate(4,season.getReleaseDate());
		seasonStatement.setBlob(5, season.getSeasonImage());
		seasonStatement.setInt(6, season.getId());
		seasonStatement.executeUpdate();
		connect.close();
		
	}
	@Override
	public List<String> retriveSeriesName(String pattern) throws SQLException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<>();
		String seriesSql="SELECT Series_name from series_list where Series_name like ?";
		PreparedStatement nameStatement= connect.prepareStatement(seriesSql);
		nameStatement.setString(1, pattern+"%");
		ResultSet nameSet=nameStatement.executeQuery();
		while (nameSet.next()) {
			list.add(nameSet.getString(1));
		}
		return list;
		
	}
	

}
