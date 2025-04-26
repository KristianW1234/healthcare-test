package com.testing.healthcare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Value;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Value("${endpoint.username}")
	private String epUser;
	
	@Value("${endpoint.password}")
	private String epPassword;
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/healthcare/**").authenticated()
	                .anyRequest().permitAll()
	            )
	            .httpBasic(withDefaults()) // enable basic auth
	            .build();
	}
	
	@Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
            .username(epUser)
            .password("{noop}"+epPassword)
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
