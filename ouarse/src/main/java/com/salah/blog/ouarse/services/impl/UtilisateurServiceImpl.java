package com.salah.blog.ouarse.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salah.blog.ouarse.entities.Utilisateur;
import com.salah.blog.ouarse.repository.UserRepository;
import com.salah.blog.ouarse.services.UtilisateurService;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	UserRepository userRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Utilisateur findByUserName(String username) {
		return userRepository.findByUserName(username);

	}

}
