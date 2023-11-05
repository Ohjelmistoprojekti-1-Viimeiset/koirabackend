package com.viimeiset.koiranvaatekauppa;


import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.viimeiset.koiranvaatekauppa.web.UserDetailServiceImpl;



@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig  {
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeHttpRequests(authorize -> authorize
				
				
				.requestMatchers(antMatcher("/css/**")).permitAll()
				.requestMatchers(antMatcher("/signup")).permitAll()
				.requestMatchers(antMatcher("/saveuser")).permitAll()
				.requestMatchers(antMatcher("/tuotelista")).permitAll()
				.requestMatchers(antMatcher("/valmistajalista")).permitAll()
				
				
				.requestMatchers(antMatcher("/h2-console/**")).hasRole("ADMIN")
				.requestMatchers(antMatcher("/lisaa/**")).hasRole("ADMIN")
				.requestMatchers(antMatcher("/muokkaa/**")).hasRole("ADMIN")
				.requestMatchers(antMatcher("/delete/{id}")).hasRole("ADMIN")
				.requestMatchers(antMatcher("/deleteValmistaja/{id}")).hasRole("ADMIN")
				.anyRequest().authenticated()
				
		)
		
		.headers(headers -> headers
				.frameOptions(frameoptions -> 
				frameoptions.disable()
			    )
		)
		.formLogin(formlogin -> formlogin
				.loginPage("/login")
				.defaultSuccessUrl("/tuotelista", true)
				.permitAll()
		)
		.logout(logout -> logout
				.permitAll()
		);
		
		http.csrf().disable();
				
		return http.build();
	}
	


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}}