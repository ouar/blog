package com.salah.blog.ouarse.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.salah.blog.ouarse.dto.UserDto;
import com.salah.blog.ouarse.entities.Role;
import com.salah.blog.ouarse.entities.Utilisateur;
import com.salah.blog.ouarse.repository.UserRepository;
import com.salah.blog.ouarse.security.JwtTokenUtil;
import com.salah.blog.ouarse.security.exception.CustomException;
import com.salah.blog.ouarse.services.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author salah
 *
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDto signin(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			Utilisateur authentifiedUser = userRepository.findByUserName(username);
			List<Role> listRoles = authentifiedUser.getRoles();
			String token = jwtTokenUtil.createToken(username, listRoles);

			UserDto userResponse = new UserDto();
			userResponse.setUserName(username);
			userResponse.setToken(token);
			userResponse.setRoles(listRoles);
			return userResponse;
		} catch (AuthenticationException e) {
			log.error(e.getMessage());
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String signup(Utilisateur user) {
		if (!userRepository.existsByUserName(user.getUserName())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return jwtTokenUtil.createToken(user.getUserName(), user.getRoles());
		} else {
			log.error("Username is already in use");
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
