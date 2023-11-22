package com.espotify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final UserAuthenticationProvider userAuthenticationProvider;
	private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.exceptionHandling(handling -> handling.authenticationEntryPoint(userAuthenticationEntryPoint))
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests
                                .requestMatchers(HttpMethod.GET, "/song", "/song/{songId}", "/new-songs", 
                                    "/artist", "/artist/{artistId}",
                                    "/thumbnail", "/thumbnail/{thumbnailId}",
                                    "/storage/image/{id}", "/storage/audio/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                                .anyRequest().authenticated()
                );
                // .authorizeHttpRequests(requests -> requests.anyRequest().permitAll());
		return http.build();
	}
}
