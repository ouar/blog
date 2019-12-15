package com.salah.blog.ouarse.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author salah
 *
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "ouarse.datasource")
@Component
public class ApplicationDatasourceProperties {

	/*
	 * 
	 */
	private String url;
	/*
	 * 
	 */
	private String username;
	/*
	 * 
	 */
	private String password;

}