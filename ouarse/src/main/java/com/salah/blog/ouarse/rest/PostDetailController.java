package com.salah.blog.ouarse.rest;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salah.blog.ouarse.dto.PostDetailDto;
import com.salah.blog.ouarse.entities.Post;
import com.salah.blog.ouarse.entities.PostDetail;
import com.salah.blog.ouarse.security.exception.CustomException;
import com.salah.blog.ouarse.services.PostDetailService;
import com.salah.blog.ouarse.services.PostService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/postDetail/")
@Slf4j
public class PostDetailController {

	@Autowired
	PostDetailService postDetailService;

	@Autowired
	PostService postService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "{idPost}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostDetailDto>> getListPostDetails(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("idPost") int idPost) {
		try {
			List<PostDetailDto> listPostDetails = postDetailService.getListPostDetails(idPost);
			return ResponseEntity.ok(listPostDetails);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "{idPost}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostDetailDto>> createPostDetail(@AuthenticationPrincipal UserDetails userDetails,
			@RequestBody PostDetailDto postDetailDto, @PathVariable("idPost") int idPost) {
		try {
			PostDetail postDetail = modelMapper.map(postDetailDto, PostDetail.class);
			Post post = postService.findById(idPost);
			postDetail.setPost(post);
			postDetailService.createPostDetail(postDetail);
			List<PostDetailDto> listPostDetails = postDetailService.getListPostDetails(idPost);
			return ResponseEntity.ok(listPostDetails);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
