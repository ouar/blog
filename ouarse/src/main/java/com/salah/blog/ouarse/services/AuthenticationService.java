package com.salah.blog.ouarse.services;

import com.salah.blog.ouarse.dto.UserDto;
import com.salah.blog.ouarse.entities.Utilisateur;

/**
 * 
 * @author salah
 *
 */
public interface AuthenticationService {
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	UserDto signin(String username, String password);

	/**
	 * 
	 * @param user
	 * @return
	 */
	String signup(Utilisateur user);

}
