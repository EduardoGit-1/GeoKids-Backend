package com.example.GeoKidsBackend.payload;
import javax.validation.constraints.*;

public class SignUpRequest {

	@NotBlank
	@Size(min=4, max = 15)
	private String nickname;
	
	@NotBlank
	private int characterID;

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
	
	
}
