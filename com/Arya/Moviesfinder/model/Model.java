package com.Arya.Moviesfinder.model;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class Model {
	
	protected List<String> genereList=null;
	public static Map<String, String> GENRE=new HashMap<String, String>();
	public static  File IMAGE=null;
	protected InputStream image=null;
	protected String id=null;
	protected boolean interested;
	protected boolean favourite;
	protected boolean watched;
	protected String language=null;
	
	public String listString() {
		String list="";
		for (String string : genereList) {
			
			list=list+string+", ";
		}
		return list;
	}

	@Override
	public String toString() {
		return  genereList.get(0) ;
	}

	public List<String> getGenereList() {
		return genereList;
	}

	public void setGenereList(List<String> genereList) {
		this.genereList = genereList;
	}

	public boolean isInterested() {
		return interested;
	}

	public void setInterested(boolean interested) {
		this.interested = interested;
	}

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}

	public boolean isWatched() {
		return watched;
	}

	public void setWatched(boolean watched) {
		this.watched = watched;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(id.equals(((Model)obj).id)){ 
			return true; 
			} 
		return false;
	}
	
	

}
