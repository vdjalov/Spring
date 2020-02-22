package app.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http
			.authorizeRequests()
			.antMatchers("/users/login", "/users/register", "/index", "/user").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/users/login").permitAll()
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/home", true)
			.failureUrl("/login?error").permitAll()
			.and()
			.logout().logoutSuccessUrl("/login?logout").permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/unauthorized");
	}
}
