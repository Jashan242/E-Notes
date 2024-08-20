package com.enotes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationPovider=new DaoAuthenticationProvider();
		daoAuthenticationPovider.setUserDetailsService(userDetailsService);
		daoAuthenticationPovider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationPovider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().requestMatchers("/users/**").hasRole("USER").requestMatchers("/**")
		.permitAll().and().formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/user/addNotes").permitAll();
		return http.build();
	}
}
