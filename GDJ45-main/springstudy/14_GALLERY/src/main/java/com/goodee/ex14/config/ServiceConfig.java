package com.goodee.ex14.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex14.service.GalleryService;
import com.goodee.ex14.service.GalleryServiceImpl;

@Configuration
public class ServiceConfig {

	@Bean
	public GalleryService galleryService() {
		return new GalleryServiceImpl();
	}
	
}
