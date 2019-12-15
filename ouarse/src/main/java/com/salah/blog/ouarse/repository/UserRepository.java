package com.salah.blog.ouarse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salah.blog.ouarse.entities.Utilisateur;

@Repository
public interface UserRepository extends JpaRepository<Utilisateur, String> {

	/**
	 * 
	 * @param username
	 * @return
	 */
	Utilisateur findByUserName(String username);

	/**
	 * 
	 * @param userName
	 * @return
	 */
	boolean existsByUserName(String userName);

}
