package com.example.GeoKidsBackend.model;

import org.springframework.data.annotation.Id;
public class GeoKid {

	@Id
	private String id;
	
	private String nickname;
	private int characterID;
	
	public GeoKid() {}
	
	public GeoKid(String nickname, int characterID) {
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

	public int getCharacterID() {
		return characterID;
	}

	public void setCharacterID(int characterID) {
		this.characterID = characterID;
	}

	public String toString() {
		return "Nickname: " + this.nickname + " CharacterID: " + this.characterID;
	}
	
	
}
