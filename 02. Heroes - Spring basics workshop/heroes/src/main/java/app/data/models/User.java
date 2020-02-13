package app.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {

	@NotEmpty(message = "username cannot be empty")
	@Column(unique = true, nullable = false)
	private String username;
	
	@NotEmpty(message = "password cannot be empty")
	private String password;
	
	@NotEmpty(message = "email cannot be empty")
	@Pattern(regexp = "[a-zA-Z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "Incorrect email")
	private String email;
	
	@OneToOne(targetEntity = Hero.class, mappedBy = "user")
	private Hero hero;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	
	
	
}
