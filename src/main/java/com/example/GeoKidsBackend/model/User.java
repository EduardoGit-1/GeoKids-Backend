package com.example.GeoKidsBackend.model;

import org.springframework.data.annotation.Id;
public class User {

	@Id
	private String id;
	
	private String nickname;
	private String characterID;
	
	public User() {}
	
	public User(String nickname, String characterID) {
		this.nickname = nickname;
		this.characterID = characterID;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCharacterID() {
		return characterID;
	}

	public void setCharacterID(String characterID) {
		this.characterID = characterID;
	}

	public String toString() {
		return "Nickname: " + this.nickname + " CharacterID: " + this.characterID;
	}
	
	
}
