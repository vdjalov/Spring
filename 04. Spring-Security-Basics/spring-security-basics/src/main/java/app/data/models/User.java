package app.data.models;

import java.beans.Transient;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles",
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> authorities;

	public User() {
	}

	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
	public String getPassword() {
		return this.password;
	}


	@Override
	public String getUsername() {
		return this.username;
	}

	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}



	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	@Transient
	public boolean isEnabled() {
		return true;
	}
	
	
}
