package app.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User extends BaseModel {
	
	@NotEmpty(message = "Username cannot be empty")
	@Size(min = 3, max = 40, message = "Username should be between 3 and 40 letters long")
	@Column(unique = true, nullable = false)
	private String username;
	
	@NotEmpty(message = "Password cannot be empty")
	@Column(nullable = false)
	private String password;
	
	@NotEmpty(message = "Email cannot be empty")
	@Pattern(regexp = "[a-zA-Z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "Incorrect email")
	@Column(unique = true, nullable = false)
	private String email;
	
	@OneToOne(targetEntity = Hero.class, mappedBy = "user")
	private Hero hero;
	
	public User() {}

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
