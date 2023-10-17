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



public class CURDMovie implements CURDMovieDAO
{
	private Connection connection=null;
	

	public CURDMovie() throws SQLException {
		super();
		connection=new DBconnection().dbconnection();
	}

	public void insertMovie(MovieModel movie) throws SQLException { // need action
		
			String insertSql="insert into movie_list(Movie_Name, Language, Theatre_OTT,"
					+ " Theatre_Release_Date, OTT_Release_Date ,Image, favorite, interested) values (?,?,?,?,?,?,?,?)";
			PreparedStatement pstInsert=connection.prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS);
			pstInsert.setString(1, movie.getMovieName());
			pstInsert.setString(2,movie.getLanguage());
			pstInsert.setString(3,movie.getReleaseType());
			pstInsert.setDate(4,movie.getThreatreReleaseDate());
			pstInsert.setDate(5,movie.getOttReleaseDate());
			pstInsert.setBlob(6,movie.getImage());
			pstInsert.setBoolean(7, movie.isFavourite());
			pstInsert.setBoolean(8, movie.isInterested());
			pstInsert.executeUpdate();
			ResultSet idSet= pstInsert.getGeneratedKeys();
			String id=null;;
			while (idSet.next()) {
				id=idSet.getString(1);
			}
			String genreSql="insert into movie_genre values(?,?)";
			PreparedStatement genreStatement=connection.prepareStatement(genreSql);
			for (String genre : movie.getGenereList()) {
				genreStatement.setString(1, "M"+id);
				genreStatement.setString(2, Model.GENRE.get(genre));
				genreStatement.executeUpdate();
				
			}
			connection.close();
		
	}

	public void updateMovie(MovieModel movie)throws SQLException { 
		
		String insertSql="update  movie_list set Movie_Name=?,Language=?,Theatre_OTT=?,Theatre_Release_Date=?,"
				+ "OTT_Release_Date=?,Image=?,favorite=?,interested=?  where Movie_ID=?";
		PreparedStatement pstInsert=connection.prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS);
		pstInsert.setString(1, movie.getMovieName());
		pstInsert.setString(2,movie.getLanguage());
		pstInsert.setString(3,movie.getReleaseType());
		pstInsert.setDate(4,movie.getThreatreReleaseDate());
		pstInsert.setDate(5,movie.getOttReleaseDate());
		pstInsert.setBlob(6,movie.getImage());
		pstInsert.setBoolean(7, movie.isFavourite());
		pstInsert.setBoolean(8, movie.isInterested());
		pstInsert.setString(9, movie.getId());
		pstInsert.executeUpdate();
		String deleteSql="delete from movie_genre where movie_id=?";
		PreparedStatement deleteStatement=connection.prepareStatement(deleteSql);
		deleteStatement.setString(1, movie.getId());
		deleteStatement.executeUpdate();
		String genreSql="insert into movie_genre values(?,?)";
		PreparedStatement genreStatement=connection.prepareStatement(genreSql);
		for (String genre : movie.getGenereList()) {
			genreStatement.setString(1,movie.getId());
			genreStatement.setString(2, Model.GENRE.get(genre));
			genreStatement.executeUpdate();
			
		}
		connection.close();
		
	}

	public List<String> retriveGenre(String id) throws SQLException {
		// TODO Auto-generated method stub
		List<String> genreList=new ArrayList<String>();
		String genreSql=null;
		ResultSet genreSet=null;
		if(id==null) {
		genreSql="select * from genre";
		genreSet=connection.createStatement().executeQuery(genreSql);
		genreList.add("Select a genre");
		while(genreSet.next()) {
			genreList.add(genreSet.getString(2));
			Model.GENRE.put(genreSet.getString(2),genreSet.getString(1));
			
		}
		return genreList;
		}
		genreSql="SELECT genre.genre_name FROM genre inner join movie_genre"
				+ " on movie_genre.genre_id=genre.genre_id where movie_genre.movie_id='"
				+id+"'";
		genreSet=connection.createStatement().executeQuery(genreSql);
		while(genreSet.next()) {
			genreList.add(genreSet.getString(1));
			
		}
		
		return genreList;
	
	}
	
	

	public List<Model> retriveMovies(Object pattern,int type) throws SQLException {
		// TODO Auto-generated method stub
		String movieSql=null;
		PreparedStatement nameStatement=null;
		switch (type) {
		case 1:
			movieSql="SELECT  * FROM movie_list where Movie_Name like ?";
			nameStatement= connection.prepareStatement(movieSql);
			nameStatement.setString(1, pattern+"%");
			break;
		case 2:
			movieSql="SELECT  * FROM movie_list where Theatre_Release_Date=?";
			nameStatement= connection.prepareStatement(movieSql);
			nameStatement.setDate(1,(Date)pattern);
			break;
		case 3:
			movieSql="SELECT movie_list.* FROM movie_list inner join movie_genre on "
					+ "movie_list.Movie_ID=movie_genre.movie_id where genre_id=?";
			nameStatement= connection.prepareStatement(movieSql);
			nameStatement.setString(1,Model.GENRE.get(pattern));
			break;
		case 4:
			movieSql="select * from movie_list where "+pattern+"=true order by rand() limit 8";
			nameStatement= connection.prepareStatement(movieSql);
			break;
		case 5:
			movieSql="SELECT  * FROM movie_list where Theatre_Release_Date between ? and ? order by rand()";
			nameStatement= connection.prepareStatement(movieSql);
			nameStatement.setDate(1,Date.valueOf(LocalDate.now().minusDays(7)));
			nameStatement.setDate(2,Date.valueOf(LocalDate.now().plusDays(7)));
		
			break;
		case 6:
			movieSql="SELECT  * FROM movie_list where OTT_Release_Date between ? and ? order by rand()";
			nameStatement= connection.prepareStatement(movieSql);
			nameStatement.setDate(1,Date.valueOf(LocalDate.now().minusDays(7)));
			nameStatement.setDate(2,Date.valueOf(LocalDate.now().plusDays(7)));
			break;
		default:
			break;
		}
		
		
		ResultSet nameSet=nameStatement.executeQuery();
		return setMovie(nameSet);
		
	}
	private List<Model> setMovie(ResultSet nameSet) throws SQLException{
		List<Model> nameList=new ArrayList<Model>();
		MovieModel movie=null;
		while (nameSet.next()) {
			movie=new MovieModel();
			movie.setId(nameSet.getString(2));
			movie.setMovieName(nameSet.getString(3));
			movie.setLanguage(nameSet.getString(4));
			movie.setReleaseType(nameSet.getString(5));
			movie.setThreatreReleaseDate(nameSet.getDate(6));
			movie.setOttReleaseDate(nameSet.getDate(7));
			movie.setGenereList(retriveGenre(nameSet.getString(2)));
			movie.setImage( nameSet.getBinaryStream(8));
			movie.setFavourite(nameSet.getBoolean(9));
			movie.setWatched(nameSet.getBoolean(10));
			movie.setInterested(nameSet.getBoolean(11));
			nameList.add(movie);
		}
		return nameList;
	}

	public void updateChoice(int type, boolean choice,String id) throws SQLException {
		// TODO Auto-generated method stub
		String choiceUpdate=null;
		PreparedStatement choiceStatement=null;
		switch (type) {
		case 1:
			choiceUpdate="update movie_list set favorite=? where Movie_ID=?;";
			
			break;
		case 2:
			choiceUpdate="update movie_list set watched=? where Movie_ID=?;";
			break;

		default:
			break;
		}
		choiceStatement=connection.prepareStatement(choiceUpdate);
		choiceStatement.setBoolean(1, choice);
		choiceStatement.setString(2, id);
		choiceStatement.executeUpdate();
		connection.close();
		
		
	}

	@Override
	public List<String> retriveMovieName(String pattern) throws SQLException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<>();
		String movieSql="SELECT Movie_Name  FROM movie_list where Movie_Name like ?";
		PreparedStatement nameStatement= connection.prepareStatement(movieSql);
		nameStatement.setString(1, pattern+"%");
		ResultSet nameSet=nameStatement.executeQuery();
		while (nameSet.next()) {
			list.add(nameSet.getString(1));
		}
		return list;
	}
	
	
}
