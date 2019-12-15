package com.salah.blog.ouarse.rest;

import java.time.Instant;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salah.blog.ouarse.dto.PostDto;
import com.salah.blog.ouarse.entities.Post;
import com.salah.blog.ouarse.entities.PostDetail;
import com.salah.blog.ouarse.entities.Utilisateur;
import com.salah.blog.ouarse.security.exception.CustomException;
import com.salah.blog.ouarse.services.PostService;
import com.salah.blog.ouarse.services.UtilisateurService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/posts")
@Slf4j
public class PostController {

	@Autowired
	PostService postService;

	@Autowired
	UtilisateurService utilisateurService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostDto>> getListPosts() {
		try {
			List<PostDto> listPost = postService.getListPosts();
			return ResponseEntity.ok(listPost);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<PostDto>> createPost(@AuthenticationPrincipal UserDetails userDetails,
			@RequestBody PostDto postDto) {
		try {
			postDto.setCreatedAt(Instant.now());
			postDto.setUpdatedAt(Instant.now());
			Post post = modelMapper.map(postDto, Post.class);		
			for (PostDetail postDetail : post.getPostDetails()) {
				postDetail.setCreatedAt(Instant.now());
				postDetail.setUpdatedAt(Instant.now());
				postDetail.setPost(post);				
			}
			Utilisateur utilisateur = utilisateurService.findByUserName(userDetails.getUsername());
			post.setUtilisateur(utilisateur);
		
			postService.createPost(post);
			List<PostDto> listPost = postService.getListPosts();
			return ResponseEntity.ok(listPost);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
