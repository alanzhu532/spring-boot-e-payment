package com.staxrt.tutorial;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class corsConfigurationSource {
	
	/*
	 * @Bean CorsConfigurationSource corsConfigurationSource() { CorsConfiguration
	 * configuration = new CorsConfiguration();
	 * configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
	 * configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH", "PUT",
	 * "DELETE", "OPTIONS", "HEAD")); configuration.setAllowCredentials(false);
	 * configuration.setAllowedHeaders(Arrays.asList("Authorization",
	 * "Requestor-Type"));
	 * configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
	 * configuration.setMaxAge(3600L); UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
	 * configuration); return source; }
	 */
	
	@Bean("corsConfigurer")
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/v1").allowedOrigins("http://localhost:8080"); 
			}
		};
	}

}
