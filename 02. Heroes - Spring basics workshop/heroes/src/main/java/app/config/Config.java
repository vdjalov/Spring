package app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.utils.UserUtils;
import app.utils.UserUtilsImpl;

@Configuration
public class Config {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public UserUtils userUtils() {
		return new UserUtilsImpl();
	}
}
