package com.salah.blog.ouarse.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salah.blog.ouarse.dto.PostDto;
import com.salah.blog.ouarse.entities.Post;
import com.salah.blog.ouarse.repository.PostRepository;
import com.salah.blog.ouarse.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<PostDto> getListPosts() {
		return postRepository.findAll().stream().map(post -> modelMapper.map(post, PostDto.class))
				.filter(Objects::nonNull).collect(Collectors.toList());
	}

	@Override
	public void createPost(Post post) {
		postRepository.save(post);
	

	}

	@Override
	public Post findById(int id) {
		return postRepository.findById(id).orElse(null);

	}

}
