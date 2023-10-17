package com.Arya.Moviesfinder.model;

import java.sql.SQLException;
import java.util.List;

public interface CURDMovieDAO {

	
	void insertMovie(MovieModel movie)throws SQLException;
	void updateMovie(MovieModel movie) throws SQLException;
	List<String> retriveGenre(String id)throws SQLException;
	List<Model> retriveMovies(Object pattern,int type)throws SQLException;
	void updateChoice(int type, boolean choice,String  id)throws SQLException;
	List<String> retriveMovieName(String pattern)throws SQLException;
	
	
}

