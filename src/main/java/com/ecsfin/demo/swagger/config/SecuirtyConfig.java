package com.ecsfin.demo.swagger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecuirtyConfig {

	@Autowired
	JwtAuthConverter jwtAuthConverter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(c -> c.disable())
				.authorizeHttpRequests(a -> a
						.requestMatchers("/swagger-ui/**",
										"/v3/api-docs/**",
										"/swagger-resources/**"
										).permitAll()
						.anyRequest().authenticated())
				.oauth2ResourceServer(o -> o.jwt(j -> j.jwtAuthenticationConverter(jwtAuthConverter)))
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}
}
