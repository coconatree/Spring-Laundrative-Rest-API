package com.laundrative_v2.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * Some questions about this file ?
 *
 */

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer
{
	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer()
		{
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
				List<String> allowedMethods = new ArrayList<>();
				allowedMethods.add("GET");
				allowedMethods.add("POST");
				allowedMethods.add("PUT");
				allowedMethods.add("DELETE");

				List<String> origins = new ArrayList<>();
				origins.add("https://laundry.bil-wash.com");
				origins.add("http://localhost");
				origins.add("capacitor://localhost");

				List<String> exposedHeaders = new ArrayList<String>();
				exposedHeaders.add("Access-Control-Allow-Headers");
				exposedHeaders.add("Origin");
				exposedHeaders.add("Accept, X-Requested-With");
				exposedHeaders.add("Content-Type");
				exposedHeaders.add("Access-Control-Request-Method");
				exposedHeaders.add("Access-Control-Request-Headers");
				//@formatter:off
				registry.addMapping("/**")
				  .allowedMethods(allowedMethods.toArray(new String[allowedMethods.size()]))
				  .allowCredentials(true).allowedOrigins(origins.toArray(new String[origins.size()]))
				  .exposedHeaders(exposedHeaders.toArray(new String[exposedHeaders.size()]));
				//@formatter:on
			}
		};
	}

}