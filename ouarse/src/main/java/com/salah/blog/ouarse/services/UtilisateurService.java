package com.salah.blog.ouarse.services;

import com.salah.blog.ouarse.entities.Utilisateur;

public interface UtilisateurService {

	Utilisateur findByUserName(String username);

}
