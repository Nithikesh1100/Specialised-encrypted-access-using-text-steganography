package com.springauth.SpringAuth.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.RegExpAllowFromStrategy;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.cors(Customizer.withDefaults())
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(req -> req
	            .requestMatchers("/api/v1/auth/**", "/api/questions/**","/api/encryption/**", "/api/**").permitAll()
	            .requestMatchers("/admin/**").hasRole("ADMIN")
//	            .requestMatchers("/analyst/**").hasRole("BUSINESS_ANALYST")
	            .requestMatchers("/client/**").hasRole("CLIENT")
	            .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
	            .anyRequest().authenticated()
	        )
    .formLogin(Customizer.withDefaults())
		    .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
