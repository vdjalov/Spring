package app.web.models;

import app.data.models.enums.Gender;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HeroFightViewModel {

	
	private String userHeroName;
	private String enemyHeroName;
	private Gender userHeroGender;
	private Gender enemyHeroGender;
	private String winnerName;
	
	
	public String getUserHeroName() {
		return userHeroName;
	}
	public void setUserHeroName(String userHeroName) {
		this.userHeroName = userHeroName;
	}
	public String getEnemyHeroName() {
		return enemyHeroName;
	}
	public void setEnemyHeroName(String enemyHeroName) {
		this.enemyHeroName = enemyHeroName;
	}
	public Gender getUserHeroGender() {
		return userHeroGender;
	}
	public void setUserHeroGender(Gender userHeroGender) {
		this.userHeroGender = userHeroGender;
	}
	public Gender getEnemyHeroGender() {
		return enemyHeroGender;
	}
	public void setEnemyHeroGender(Gender enemyHeroGender) {
		this.enemyHeroGender = enemyHeroGender;
	}
	public String getWinnerName() {
		return winnerName;
	}
	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}
	
	
	
	
	
}
