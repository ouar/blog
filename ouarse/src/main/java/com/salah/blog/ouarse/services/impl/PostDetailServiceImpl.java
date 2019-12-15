package com.salah.blog.ouarse.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salah.blog.ouarse.dto.PostDetailDto;
import com.salah.blog.ouarse.entities.PostDetail;
import com.salah.blog.ouarse.repository.PostDetailRepository;
import com.salah.blog.ouarse.services.PostDetailService;

@Service
public class PostDetailServiceImpl implements PostDetailService {

	@Autowired
	PostDetailRepository postDetailRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<PostDetailDto> getListPostDetails(int idPost) {
		return postDetailRepository.findAll().stream()
				.map(postDetail -> modelMapper.map(postDetail, PostDetailDto.class)).filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	@Override
	public void createPostDetail(PostDetail postDetail) {
		postDetailRepository.save(postDetail);

	}

}
