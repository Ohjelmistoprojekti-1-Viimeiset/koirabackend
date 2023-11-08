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
import org.springframework.web.cors.CorsConfiguration;
import java.util.Arrays;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.viimeiset.koiranvaatekauppa.web.UserDetailServiceImpl;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(authorize -> authorize

						.requestMatchers(antMatcher("/css/**")).permitAll()
						.requestMatchers(antMatcher("/signup")).permitAll()
						.requestMatchers(antMatcher("/saveuser")).permitAll()
						.requestMatchers(antMatcher("/tuotelista")).permitAll()
						.requestMatchers(antMatcher("/valmistajalista")).permitAll()
						.requestMatchers(antMatcher("/index")).permitAll()

						.requestMatchers(antMatcher("/h2-console/**")).hasRole("ADMIN")
						.requestMatchers(antMatcher("/lisaa/**")).hasRole("ADMIN")
						.requestMatchers(antMatcher("/muokkaa/**")).hasRole("ADMIN")
						.requestMatchers(antMatcher("/delete/{id}")).hasRole("ADMIN")
						.requestMatchers(antMatcher("/deleteValmistaja/{id}")).hasRole("ADMIN")
						.requestMatchers(antMatcher("/api/**")).permitAll()
						.anyRequest().authenticated()

				)

				.headers(headers -> headers
						.frameOptions(frameoptions -> frameoptions.disable()))
				.formLogin(formlogin -> formlogin
						.loginPage("/login")
						.defaultSuccessUrl("/index", true)
						.permitAll())
				.logout(logout -> logout
						.logoutSuccessUrl("/index")
						.permitAll())

				.csrf(csrf -> csrf.disable());

		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}