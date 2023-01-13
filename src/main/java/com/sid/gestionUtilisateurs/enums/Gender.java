package com.sid.gestionUtilisateurs.enums;

/**
 * 
 * @author A734647
 *
 */

public enum Gender {
	M("M"),F("F");
	
	private String genre ;

	Gender(String genre) {
		this.genre = genre ;
	}

	public String getGenre() {
		return genre;
	}

	
}
